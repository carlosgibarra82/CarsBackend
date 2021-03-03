package com.cardemo.backend.core.users.services;

import com.cardemo.backend.core.users.entities.UserEntity;
import com.cardemo.backend.core.users.services.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public UserEntity create(UserEntity userEntity) {
        this.createValidations(userEntity);

        return userRepository.save(userEntity);
    }

    private void createValidations(UserEntity userEntity) {
        this.idNumberValidation(userEntity.getId());
        this.emailValidation(userEntity.getEmail());
    }

    public UserEntity update(UserEntity newUserEntity) {
        UserEntity userEntity = userRepository.findById(newUserEntity.getId())
                .orElseThrow(() -> new ExpressionException("User doesn't exists"));

        this.updateValidations(userEntity, newUserEntity);

        userEntity.setId(newUserEntity.getId());
        userEntity.setFirstName(newUserEntity.getFirstName());
        userEntity.setLastName(newUserEntity.getLastName());
        userEntity.setBirthDate(newUserEntity.getBirthDate());
        userEntity.setEmail(newUserEntity.getEmail());
        userEntity.setPassword(newUserEntity.getPassword());

        return userRepository.save(userEntity);
    }

    private void updateValidations(UserEntity userEntity, UserEntity newUserEntity) {
        if (!(userEntity.getId() == newUserEntity.getId())) {
            this.idNumberValidation(newUserEntity.getId());
        }

        if (!userEntity.getEmail().equals(newUserEntity.getEmail())) {
            this.emailValidation(newUserEntity.getEmail());
        }
    }

    private void idNumberValidation(Long id) {
        if (userRepository.existsById(id)) {
            throw new ExpressionException("ID already exists");
        }
    }

    private void emailValidation(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ExpressionException("eMail already exists");
        }
    }

    public UserEntity getOne(Long id) {
        return userRepository.getOne(id);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

//    public List<UserEntity> search(String search) {
//        return userRepository.search(search.toUpperCase());
//    }
}
