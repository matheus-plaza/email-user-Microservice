package io.github.matheusplaza.user.producer;

import io.github.matheusplaza.user.dto.MailDTO;
import io.github.matheusplaza.user.entity.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${QUEUE_NAME}")
    private String queueName;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMail(UserModel userModel) {
        rabbitTemplate.convertAndSend(
                "", //direct / default,
                queueName,
                MailDTO.builder()
                        .userId(userModel.getUserId())
                        .emailTo(userModel.getUserMail())
                        .subject("Welcome " + userModel.getUserName())
                        .body("Hello " + userModel.getUserName() + ", \n\n Enjoy my application! ")
                        .build());
    }
}
