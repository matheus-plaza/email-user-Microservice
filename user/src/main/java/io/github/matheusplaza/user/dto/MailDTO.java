package io.github.matheusplaza.user.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MailDTO(
        UUID userId,
        String emailTo,
        String subject,
        String body
) {
}
