package br.com.mailio.application.service.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class GetAccessTokenService {
    @Autowired
    private OAuth2AuthorizedClientService clientService;

    public String getAccessToken(Authentication auth) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient("google", auth.getName());

        return client.getAccessToken().getTokenValue();
    }
}
