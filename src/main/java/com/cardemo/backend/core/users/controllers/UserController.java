package com.cardemo.backend.core.users.controllers;

import com.cardemo.backend.core.users.controllers.dto.UserDto;
import com.cardemo.backend.core.users.entities.UserEntity;
import com.cardemo.backend.core.users.mappers.IUserMapper;
import com.cardemo.backend.core.users.services.UserService;

import com.cardemo.backend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        UserEntity user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id :" + id + "Doesn't exists"));
        userService.delete(user.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
