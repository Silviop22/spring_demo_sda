package com.example.demo.controller;

import com.example.demo.model.ErrorResponse;
import com.example.demo.model.User;
import com.example.demo.model.ui.UserDto;
import com.example.demo.model.ui.UserExtendedDto;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    
    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserExtendedDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }
    
    @GetMapping
    public ResponseEntity<Set<UserDto>> getList() {
        return ResponseEntity.ok(service.getList());
    }
    
    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserExtendedDto user) {
        User result = service.createUser(user);
        URI location = ServletUriComponentsBuilder
                               .fromCurrentRequest()
                               .path("/{id}")
                               .buildAndExpand(result.getId())
                               .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.ok().build();
    }
}
