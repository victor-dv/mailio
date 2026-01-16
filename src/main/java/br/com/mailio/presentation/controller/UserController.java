package br.com.mailio.presentation.controller;

import br.com.mailio.application.dto.user.AuthRequestDto;
import br.com.mailio.application.dto.user.RegisterUserDto;
import br.com.mailio.application.dto.user.UpdateUserDto;
import br.com.mailio.application.service.user.AuthUserService;
import br.com.mailio.application.service.user.RegisterUser;
import br.com.mailio.application.service.user.UpdateUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private RegisterUser registerUser;
    @Autowired
    private UpdateUser updateUser;
    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        var result = registerUser.execute(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto) {
        var token =this.authUserService.execute(authRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        var result = updateUser.execute(id, updateUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
