package mps2k1.taskmanagementsystem.mapper;

import mps2k1.taskmanagementsystem.dto.UserDTO;
import mps2k1.taskmanagementsystem.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class UserMapper {

   public UserDTO map(UserEntity userEntity){
        UserDTO dto = UserDTO.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .build();
        return dto;
    }

    public UserEntity mapToDao(UserDTO dto){
        UserEntity user = UserEntity.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .login(dto.getLogin())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .build();
        return user;
    }
}
