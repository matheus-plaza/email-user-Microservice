package io.github.matheusplaza.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserDTO(
        @NotBlank
        String userName,
        @Email
        @NotBlank
        String userMail) {
}
