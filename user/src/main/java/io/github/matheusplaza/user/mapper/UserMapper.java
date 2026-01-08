package io.github.matheusplaza.user.mapper;

import io.github.matheusplaza.user.dto.UserDTO;
import io.github.matheusplaza.user.entity.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserModel toUserModel(UserDTO userDTO) {

        return UserModel.builder()
                .userName(userDTO.userName())
                .userMail(userDTO.userMail())
                .build();
    }

    public UserDTO toUserDTO(UserModel userModel) {
        return UserDTO.builder()
                .userName(userModel.getUserName())
                .userMail(userModel.getUserMail())
                .build();
    }
}
