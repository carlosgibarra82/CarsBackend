package com.cardemo.backend.core.users.mappers;

import com.cardemo.backend.core.users.controllers.dto.UserDto;
import com.cardemo.backend.core.users.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IUserMapper {
    IUserMapper MAPPER = Mappers.getMapper(IUserMapper.class);

    UserDto userEntityToUserDto(UserEntity userEntity);

    UserEntity createUserDtoToUserEntity(UserDto createUserDTO);

    UserEntity updateUserDtoToUserEntity(UserDto updateUserDTO);

    List<UserDto> userEntitiesToUserDto(List<UserEntity> userEntities);
}
