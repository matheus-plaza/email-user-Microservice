package io.github.matheusplaza.mail.producer;

import io.github.matheusplaza.mail.dto.MailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${ERROR_EMAIL_QUEUE}")
    private String errorQueueName;

    public void sendErrorMessage(MailDTO mailDTO) {
        rabbitTemplate.convertAndSend("", errorQueueName, mailDTO);
    }
}
