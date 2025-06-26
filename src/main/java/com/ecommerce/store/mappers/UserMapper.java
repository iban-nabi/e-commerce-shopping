package com.ecommerce.store.mappers;

import com.ecommerce.store.dtos.RegisterUserRequest;
import com.ecommerce.store.dtos.UpdateUserRequest;
import com.ecommerce.store.dtos.UserDto;
import com.ecommerce.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest registerUserRequest);

    // use for mapping when updating a user entity, using a update user request dto
    void updateUser(UpdateUserRequest updateUserRequest, @MappingTarget User user);
}