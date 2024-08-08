package service.impl;

import dto.auth.AuthCreateUserRequest;
import dto.auth.AuthLoginRequest;
import dto.auth.AuthResponse;
import model.RoleEntity;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import persistence.IRoleRepository;
import persistence.IUserRepository;
import utils.JwtUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " doesn't exist"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())))
        );
        user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission ->
                        authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                );

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialNonExpired(),
                authorityList);
    }


    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        //recupero los datos del usuario
        String username = authLoginRequest.username();
        String password = authLoginRequest.passwrod();

        Authentication authentication = this.authenticate(username,password);

        //guardo la autenticacion en securityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //creo el token
        String accesToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User loged succesfully", accesToken, true);
    }

    public Authentication authenticate(String username, String password) {
        //recupero el user de la db
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        //devuelvo el password de userDetails ya que esta encriptado
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest userRequest) {
        //datos del usuario
        String username = userRequest.username();
        String email = userRequest.email();
        String password = userRequest.password();
        List<String> roleRequest = userRequest.roleRequest().roleListName();
        String firstName = userRequest.firstName();
        String lastName = userRequest.lastName();
        String address = userRequest.address();
        String phoneNumber = userRequest.phoneNumber();

        //chequear si roles que envio matchean con los de la tabla, puedo enviar CONTRATISTA por ej y ese no existe
        Set<RoleEntity> roleEntitySet = roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if (roleEntitySet.isEmpty()){
            throw new IllegalArgumentException("The roles specified does not exits.");
        }

        //encripto el password
        String encodedPassword = passwordEncoder.encode(password);

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phoneNumber(phoneNumber)
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialNonExpired(true)
                .build();

        //guardo user en db
        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        //obtengo roles
        userCreated.getRoles().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())))
        );
        //obtengo permisos
        userCreated.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission ->
                        authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                );


        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword(), authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        // Puedes devolver un AuthResponse con la información que necesites, por ejemplo, el username
        return new AuthResponse(username, "User created successfully",accessToken,true);
    }
}


