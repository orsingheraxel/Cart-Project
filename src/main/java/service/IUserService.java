package service;

import dto.UserCreationDTO;
import dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);
    UserCreationDTO createUser(UserCreationDTO userDTO);
    Optional<UserDTO> updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
