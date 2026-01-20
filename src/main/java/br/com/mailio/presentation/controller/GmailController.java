package br.com.mailio.presentation.controller;

import br.com.mailio.application.service.gmail.ListEmailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

public class GmailController {
    @Autowired
    private ListEmailsService listEmailsService;

    @GetMapping("/messages")
    public ResponseEntity<?> listEmails(Authentication authentication) {
        return ResponseEntity.ok(
                listEmailsService.listarEmails(authentication)
        );
    }
}
