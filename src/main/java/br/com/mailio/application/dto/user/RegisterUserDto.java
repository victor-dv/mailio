package br.com.mailio.application.dto.user;

public record RegisterUserDto(
        String name,
        String username,
        String email,
        String password,
        Boolean status
) {
}
