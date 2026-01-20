package br.com.mailio.application.service.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.client.RestTemplate;

public class ListEmailsService {
    @Autowired
    private GetAccessTokenService tokenService;

    public String listarEmails(Authentication authentication) {

        String accessToken = tokenService.getAccessToken(authentication);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "https://gmail.googleapis.com/gmail/v1/users/me/messages",
                        HttpMethod.GET,
                        entity,
                        String.class
                );

        return response.getBody();
    }
}
