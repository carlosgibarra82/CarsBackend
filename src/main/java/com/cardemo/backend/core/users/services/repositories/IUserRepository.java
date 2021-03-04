package com.cardemo.backend.core.users.services.repositories;

import com.cardemo.backend.core.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsById(Long id);

    boolean existsByEmail(String email);

}
