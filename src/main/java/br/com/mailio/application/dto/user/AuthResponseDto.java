package br.com.mailio.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AuthResponseDto {

    private String access_token;
    private Long expires_in;
    private String message;
}
