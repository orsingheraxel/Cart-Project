package service.impl;

import dto.UserEntityDTO;
import mapper.UserEntityMapper;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.IUserEntityRepository;
import service.IUserEntityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class
UserEntityServiceImpl implements IUserEntityService {

    @Autowired
    private IUserEntityRepository userRepository;

    @Override
    public List<UserEntityDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserEntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntityDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserEntityMapper::toDTO);
    }

    @Override
    public UserEntityDTO createUser(UserEntityDTO userDTO) {
        UserEntity user = UserEntityMapper.toEntity(userDTO);
        UserEntity savedUser = userRepository.save(user);
        return UserEntityMapper.toDTO(savedUser);
    }

    @Override
    public Optional<UserEntityDTO> updateUser(Long id, UserEntityDTO userDTO) {
        if (!userRepository.existsById(id)) {
            return Optional.empty();
        }

        UserEntity user = UserEntityMapper.toEntity(userDTO);
        user.setId(id);
        UserEntity updatedUser = userRepository.save(user);
        return Optional.of(UserEntityMapper.toDTO(updatedUser));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
