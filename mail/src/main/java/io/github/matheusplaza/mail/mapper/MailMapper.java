package io.github.matheusplaza.mail.mapper;

import io.github.matheusplaza.mail.dto.MailDTO;
import io.github.matheusplaza.mail.entity.MailModel;
import io.github.matheusplaza.mail.enums.MailStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MailMapper {

    @Value("${MAIL_USERNAME}")
    private String mailFrom;

    public MailModel toModel(MailDTO mailDTO) {
        return MailModel.builder()
                .mailTo(mailDTO.emailTo())
                .mailFrom(mailFrom)
                .mailBody(mailDTO.body())
                .mailSubject(mailDTO.subject())
                .createdAt(LocalDateTime.now())
                .userId(mailDTO.userId().toString())
                .mailStatus(MailStatus.PENDING)
                .build();
    }
}
