package br.com.mailio.application.service.user;

import br.com.mailio.application.dto.user.RegisterUserDto;
import br.com.mailio.domain.models.user.UserEntity;
import br.com.mailio.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity execute(RegisterUserDto dto) {
        UserEntity user = new UserEntity();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        String encodedPassword = passwordEncoder.encode(dto.password());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

}
