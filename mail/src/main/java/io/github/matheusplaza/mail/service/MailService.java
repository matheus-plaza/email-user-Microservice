package io.github.matheusplaza.mail.service;

import io.github.matheusplaza.mail.consumer.MailConsumer;
import io.github.matheusplaza.mail.dto.MailDTO;
import io.github.matheusplaza.mail.entity.MailModel;
import io.github.matheusplaza.mail.enums.MailStatus;
import io.github.matheusplaza.mail.mapper.MailMapper;
import io.github.matheusplaza.mail.producer.MailProducer;
import io.github.matheusplaza.mail.repository.MailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;
    private final MailMapper mailMapper;
    private final MailProducer mailProducer;

    @Transactional
    public void sendMail(MailDTO mailDTO) {
        MailModel mailModel = mailMapper.toModel(mailDTO);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailModel.getMailFrom());
            message.setTo(mailModel.getMailTo());
            message.setSubject(mailModel.getMailSubject());
            message.setText(mailModel.getMailBody());

            mailSender.send(message);

            mailModel.setMailStatus(MailStatus.SENT);
            log.info("Email sent successfully to: {}", mailDTO.emailTo());

        } catch (Exception e) {
            mailModel.setMailStatus(MailStatus.FAILED);
            log.error("Failed to send email to: {}. Error: {}", mailDTO.emailTo(), e.getMessage());
            errorSendMail(mailDTO);
        }

        mailRepository.save(mailModel);
    }

    public void errorSendMail(MailDTO mailDTO) {
        mailProducer.sendErrorMessage(mailDTO);
    }
}
