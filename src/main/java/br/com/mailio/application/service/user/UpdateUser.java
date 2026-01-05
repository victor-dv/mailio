package br.com.mailio.application.service.user;

import br.com.mailio.application.dto.user.UpdateUserDto;
import br.com.mailio.domain.models.user.UserEntity;
import br.com.mailio.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUser {

    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UUID id, UpdateUserDto dto) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setName(dto.name());
        user.setUsername(dto.username());

        userRepository.findByUsername(dto.username())
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(user.getId())) {
                        throw new RuntimeException("Username de usuário já está em uso");
                    }
                });
        return userRepository.save(user);
    }
}

