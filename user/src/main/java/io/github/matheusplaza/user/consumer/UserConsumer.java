package io.github.matheusplaza.user.consumer;

import io.github.matheusplaza.user.dto.MailDTO;
import io.github.matheusplaza.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserConsumer {

    private final UserService userService;

    @RabbitListener(queues = "${ERROR_EMAIL_QUEUE}")
    public void listenErrorMailQueue(@Payload MailDTO mailDTO) {
        log.info("Message error received : {}", mailDTO.toString());
        userService.mailError(mailDTO);
    }
}
