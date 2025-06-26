package com.ecommerce.store.controllers;

import com.ecommerce.store.dtos.RegisterUserRequest;
import com.ecommerce.store.dtos.UpdateUserRequest;
import com.ecommerce.store.dtos.UserDto;
import com.ecommerce.store.entities.User;
import com.ecommerce.store.mappers.UserMapper;
import com.ecommerce.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name="name") String sort
    ){
        if(!Set.of("name", "email").contains(sort)){
            sort = "name";
        }
        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ){
        // transform the request user dto into a User entity
        // then save the User
        User user = userMapper.toEntity(request);
        userRepository.save(user);

        // transform the user back to user dto
        UserDto userDto = userMapper.toDto(user);

        // create/build an uri, based on the newly created user
        // return a response entity of created (401), along with te User Dto, the uri is used here as well
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id, // id of the user you want to update
            @RequestBody UpdateUserRequest request // the request body/information to be updated
            ){

        User user = userRepository.findById(id).orElse(null); // find if user is present

        if(user==null){
            return ResponseEntity.notFound().build(); // if user is not found, return 404
        }

        userMapper.updateUser(request, user); // if user is present, call mapper to map the current user and the request body (update user dto)
        userRepository.save(user); // save the update

        return ResponseEntity.ok(userMapper.toDto(user)); // return the updated user
    }
}
