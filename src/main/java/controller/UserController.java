package controller;

import config.annotation.IsEmployee;
import config.exceptions.SecurityErrorHandler;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;
import org.springframework.web.bind.annotation.*;
import service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
public class UserController {

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
