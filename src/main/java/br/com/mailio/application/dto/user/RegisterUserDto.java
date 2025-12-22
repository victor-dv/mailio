package br.com.mailio.application.dto.user;

public record RegisterUserDto(
        String name,
        String email,
        String password
) {
}
