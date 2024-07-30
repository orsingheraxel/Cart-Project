package controller;

import config.annotation.IsEmployee;
import config.exceptions.SecurityErrorHandler;
import dto.UserCreationDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;
import org.springframework.web.bind.annotation.*;
import service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
public class UserController {

    @Autowired
    private CompromisedPasswordChecker passwordChecker;

    @Autowired
    private IUserService userService;

    @GetMapping
    @IsEmployee({"ADMIN","USER"})
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @IsEmployee({"ADMIN","USER"})
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @PostMapping
    public UserCreationDTO createUser(@RequestBody UserCreationDTO userCreationDTO) {
        // Web: https://haveibeenpwned.com/Passwords
        CompromisedPasswordDecision decision = passwordChecker.check(userCreationDTO.getPassword());

        //si el password esta comprometido(usado muchas veces segun la pagina) se le avisa al usuario
        if (decision.isCompromised()) {
            throw new IllegalArgumentException("Compromised Password.");
        }

        return userService.createUser(userCreationDTO);
    }

    @PutMapping("/{id}")
    @IsEmployee({"ADMIN","USER"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.of(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    @IsEmployee({"ADMIN","USER"})
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
