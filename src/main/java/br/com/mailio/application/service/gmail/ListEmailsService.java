package br.com.mailio.application.service.gmail;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ListEmailsService {

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
