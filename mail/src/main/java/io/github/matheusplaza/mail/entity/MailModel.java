package io.github.matheusplaza.mail.entity;

import io.github.matheusplaza.mail.enums.MailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_EMAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailModel {

    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID mailId;
    private String mailTo;
    private String mailFrom;
    private String mailSubject;
    @Column(columnDefinition = "TEXT")
    private String mailBody;
    private String userId;
    private MailStatus mailStatus;
    private LocalDateTime createdAt;

}
