package com.ecommerce.store.mappers;

import com.ecommerce.store.dtos.RegisterUserRequest;
import com.ecommerce.store.dtos.UserDto;
import com.ecommerce.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest registerUserRequest);
}