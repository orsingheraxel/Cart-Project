package service.impl;

import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import persistence.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

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
}


