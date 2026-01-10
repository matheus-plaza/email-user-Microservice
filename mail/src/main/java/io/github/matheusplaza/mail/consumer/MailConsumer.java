package io.github.matheusplaza.mail.consumer;

import io.github.matheusplaza.mail.dto.MailDTO;
import io.github.matheusplaza.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailConsumer {

    private final MailService mailService;

    @RabbitListener(queues = "${QUEUE_NAME}")
    public void listenMailQueue(@Payload MailDTO mailDTO) {
        log.info("Message received: {}", mailDTO);
        mailService.sendMail(mailDTO);
    }

}
