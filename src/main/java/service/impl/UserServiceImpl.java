package service.impl;

import dto.UserCreationDTO;
import dto.UserDTO;
import mapper.UserMapper;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.IUserRepository;
import service.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO);
    }

    @Override
    public UserCreationDTO createUser(UserCreationDTO userCreationDTO) {
        UserEntity user = UserMapper.toCreationEntity(userCreationDTO);
        UserEntity savedUser = userRepository.save(user);
        return UserMapper.toUserCreationDTO(savedUser);
    }

    @Override
    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) {
            return Optional.empty();
        }

        UserEntity user = UserMapper.updateEntity(userDTO, new UserEntity());
        user.setId(id);
        UserEntity updatedUser = userRepository.save(user);
        return Optional.of(UserMapper.toDTO(updatedUser));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
