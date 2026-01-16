package br.com.mailio.infraestructure.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    @Value("${security.token.secret.user}")
    private String secretKey;

    public String validadeToken(String token) {
        try {
            token = token.replace("Bearer", "");
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            System.err.println("Token inválido: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Erro na validação do JWT: " + e.getMessage());
            return null;
        }
    }
}
