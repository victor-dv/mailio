package br.com.mailio.domain.repository.user;


import br.com.mailio.domain.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional <UserEntity> findByName(String name);
}
