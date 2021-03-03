package com.cardemo.backend.core.users.services.repositories;

import com.cardemo.backend.core.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsById(Long id);

    boolean existsByEmail(String email);

//    List<UserEntity> search(String toUpperCase);
}
