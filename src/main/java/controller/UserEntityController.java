package controller;

import dto.UserEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IUserEntityService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {

    @Autowired
    private IUserEntityService userService;

    @GetMapping
    public List<UserEntityDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @PostMapping
    public UserEntityDTO createUser(@RequestBody UserEntityDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntityDTO> updateUser(@PathVariable Long id, @RequestBody UserEntityDTO userDTO) {
        return ResponseEntity.of(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
