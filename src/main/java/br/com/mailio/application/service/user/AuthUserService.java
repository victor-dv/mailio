package br.com.mailio.application.service.user;

import br.com.mailio.application.dto.user.AuthRequestDto;
import br.com.mailio.application.dto.user.AuthResponseDto;
import br.com.mailio.domain.repository.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class AuthUserService {

    @Value("${security.token.secret.user}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object execute(AuthRequestDto authRequestDto) {
        var user = userRepository.findByEmail(authRequestDto.email())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));
        var passwordMatch = passwordEncoder
                .matches(authRequestDto.password(), user.getPassword());
        if (!passwordMatch) {
            throw new RuntimeException("E-mail ou senha inválidos");
        }
        System.out.println(secretKey);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expireIn = Instant.now().plus(Duration.ofMinutes(80));
        var token = JWT.create()
                .withIssuer("Mailio")
                .withSubject(user.getId().toString())
                .withClaim("id", String.valueOf(user.getId()))
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .withExpiresAt(Date.from(expireIn))
                .sign(algorithm);

        return AuthResponseDto.builder()
                .access_token(token)
                .expires_in(expireIn.toEpochMilli())
                .build();
    }


}

