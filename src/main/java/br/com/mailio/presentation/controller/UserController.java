package br.com.mailio.presentation.controller;

import br.com.mailio.application.dto.user.RegisterUserDto;
import br.com.mailio.application.dto.user.UpdateUserDto;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        var result = registerUser.execute(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        var result = updateUser.execute(id, updateUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
