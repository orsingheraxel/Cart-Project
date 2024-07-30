package mapper;

import dto.UserCreationDTO;
import dto.UserDTO;
import model.UserEntity;

import java.time.LocalDateTime;

public class UserMapper {

    // Convertir UserEntity a UserDTO
    public static UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAddress(),
                userEntity.getPhoneNumber(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }

    // Convertir UserCreationDTO a UserEntity
    public static UserEntity toCreationEntity(UserCreationDTO userCreationDTO) {
        if (userCreationDTO == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreationDTO.getUsername());
        userEntity.setEmail(userCreationDTO.getEmail());
        userEntity.setPassword(userCreationDTO.getPassword());
        userEntity.setFirstName(userCreationDTO.getFirstName());
        userEntity.setLastName(userCreationDTO.getLastName());
        userEntity.setAddress(userCreationDTO.getAddress());
        userEntity.setPhoneNumber(userCreationDTO.getPhoneNumber());
        userEntity.setCreatedAt(LocalDateTime.now());
        // No se establece updatedAt, ya que es null por defecto

        return userEntity;
    }


    // Actualizar UserEntity desde UserDTO (por ejemplo, para actualizar un usuario existente)
    public static UserEntity updateEntity(UserDTO userDTO, UserEntity userEntity) {
        if (userDTO == null || userEntity == null) {
            return null;
        }
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setUpdatedAt(LocalDateTime.now()); // Actualizar la fecha de modificación
        return userEntity;
    }

    // Método para convertir UserEntity a UserCreationDTO
    public static UserCreationDTO toUserCreationDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserCreationDTO(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAddress(),
                userEntity.getPhoneNumber()
        );
    }
}
