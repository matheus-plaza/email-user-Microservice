package io.github.matheusplaza.user.producer;

import io.github.matheusplaza.user.dto.MailDTO;
import io.github.matheusplaza.user.entity.UserModel;
import io.github.matheusplaza.user.template.EmailTemplateBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;
    private final EmailTemplateBuilder emailTemplateBuilder;

    @Value("${SEND_EMAIL_QUEUE}")
    private String queueName;

    public void sendMail(UserModel userModel) {
        rabbitTemplate.convertAndSend(
                "",
                queueName,
                buildWelcomeMailDTO(userModel));
    }

    private MailDTO buildWelcomeMailDTO(UserModel userModel) {
        return MailDTO.builder()
                .userId(userModel.getUserId())
                .emailTo(userModel.getUserMail())
                .subject(emailTemplateBuilder.buildWelcomeSubject(userModel))
                .body(emailTemplateBuilder.buildWelcomeMessage(userModel))
                .build();
    }


}
