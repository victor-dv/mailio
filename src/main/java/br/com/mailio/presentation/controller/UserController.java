package br.com.mailio.presentation.controller;

import br.com.mailio.application.dto.user.RegisterUserDto;
import br.com.mailio.application.service.user.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private RegisterUser registerUser;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        var result = registerUser.execute(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }
}
