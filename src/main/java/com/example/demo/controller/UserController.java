package com.example.demo.controller;

import com.example.demo.model.ErrorResponse;
import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserExtendedDto;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    
    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getUserById(id));
        } catch (EntityNotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(), status.value()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(new ErrorResponse("Ops, we are having issues.", status.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(), status.value()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(new ErrorResponse("Ops, we are having issues.", status.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
