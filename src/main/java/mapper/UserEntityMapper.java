package mapper;

import dto.UserEntityDTO;
import model.UserEntity;

public class UserEntityMapper {

    public static UserEntityDTO toDTO(UserEntity user) {
        if (user == null) {
            return null;
        }

        UserEntityDTO userDTO = new UserEntityDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }

    public static UserEntity toEntity(UserEntityDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());

        return user;
    }
}
