package com.cardemo.backend.core.users.controllers;

import com.cardemo.backend.core.users.controllers.dto.UserDto;

import com.cardemo.backend.core.users.entities.UserEntity;
import com.cardemo.backend.core.users.mappers.IUserMapper;
import com.cardemo.backend.core.users.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto get(@PathVariable Long id){
        return IUserMapper.MAPPER.userEntityToUserDto((userService.getOne(id)));
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDto> search(){
        return IUserMapper.MAPPER.userEntitiesToUserDto(userService.findAll());
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto dto){
        return IUserMapper.MAPPER.userEntityToUserDto(userService.create(IUserMapper.MAPPER.createUserDtoToUserEntity(dto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto update(@PathVariable Long id, @RequestBody  UserDto dto){
        UserEntity userEntity = IUserMapper.MAPPER.updateUserDtoToUserEntity(dto);
        userEntity.setId(id);
        return IUserMapper.MAPPER.userEntityToUserDto(this.userService.update(userEntity));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return this.userService.delete(id);
    }
    
}
