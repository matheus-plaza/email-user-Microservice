package io.github.matheusplaza.mail.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record MailDTO(
        UUID id,
        String subject,
        String body
) {
}
