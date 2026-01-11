package io.github.matheusplaza.user.service;

import io.github.matheusplaza.user.dto.MailDTO;
import io.github.matheusplaza.user.entity.UserModel;
import io.github.matheusplaza.user.producer.UserProducer;
import io.github.matheusplaza.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    @Transactional
    public UserModel createUser(UserModel userModel) {
        userModel.generateRandomPassword();
        userRepository.save(userModel);
        userProducer.sendMail(userModel);
        return userModel;
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public Optional<UserModel> updateUser(UUID userId, UserModel userModel) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setUserName(userModel.getUserName());
                    existingUser.setUserMail(userModel.getUserMail());
                    return userRepository.save(existingUser);
                });
    }

    public void mailError(MailDTO mailDTO) {
        userRepository.deleteById(mailDTO.userId());
        log.info("User deleted : {}", mailDTO.emailTo());
    }
}
