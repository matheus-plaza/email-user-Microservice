package io.github.matheusplaza.user.presentation;

import io.github.matheusplaza.user.dto.UserDTO;
import io.github.matheusplaza.user.entity.UserModel;
import io.github.matheusplaza.user.mapper.UserMapper;
import io.github.matheusplaza.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userMapper.toUserModel(userDTO)));
    }

}
