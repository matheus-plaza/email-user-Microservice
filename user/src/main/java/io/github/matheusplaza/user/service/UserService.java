package io.github.matheusplaza.user.service;

import io.github.matheusplaza.user.dto.UserDTO;
import io.github.matheusplaza.user.entity.UserModel;
import io.github.matheusplaza.user.producer.UserProducer;
import io.github.matheusplaza.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    @Transactional
    public UserModel createUser(UserModel userModel) {
        userRepository.save(userModel);
        userProducer.sendMail(userModel);
        return userModel;
    }
}
