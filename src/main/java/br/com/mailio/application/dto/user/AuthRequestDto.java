package br.com.mailio.application.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(
        @NotBlank(message = "O EMAIL é obrigatório")
        String email,
        @NotBlank(message = "A PASSWORD é obrigatória")
        String password
) {
}
