package br.com.mailio.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterUserDto(
        @NotBlank(message = "Nome é obrigatório")
        String name,
        @NotBlank(message = "Username é obrigatório")
        String username,
        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,
        @Length(min = 6,max = 30, message = "Senha deve ter no mínimo 6 caracteres")
        @NotBlank(message = "Password é obrigatório")
        String password,
        Boolean status
) {
}
