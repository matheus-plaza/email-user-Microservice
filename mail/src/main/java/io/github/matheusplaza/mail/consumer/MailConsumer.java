package io.github.matheusplaza.mail.consumer;

import io.github.matheusplaza.mail.dto.MailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {

    @RabbitListener(queues = "${QUEUE_NAME}")
    public void listenMailQueue(@Payload MailDTO mailDTO) {
        System.out.println("Message received: " + mailDTO.body());
    }

}
