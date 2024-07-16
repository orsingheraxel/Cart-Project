package service;

import dto.UserEntityDTO;

import java.util.List;
import java.util.Optional;

public interface IUserEntityService {
    List<UserEntityDTO> getAllUsers();
    Optional<UserEntityDTO> getUserById(Long id);
    UserEntityDTO createUser(UserEntityDTO userDTO);
    Optional<UserEntityDTO> updateUser(Long id, UserEntityDTO userDTO);
    void deleteUser(Long id);
}
