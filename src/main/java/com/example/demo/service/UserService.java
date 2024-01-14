package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.ui.UserDto;
import com.example.demo.model.ui.UserExtendedDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.shared.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    
    public UserExtendedDto getUserById(Long id) {
        User user = findExistingUser(id);
        return mapper.toExtendedDto(user);
    }
    
    public Set<UserDto> getList() {
        return repository.findAll()
                       .stream()
                       .map(mapper::toDto)
                       .collect(Collectors.toSet());
    }
    
    public User createUser(UserExtendedDto user) {
        Optional<User> existingUser = repository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new EntityExistsException("User with username " + user.getUserName() + " already exists");
        }
        return repository.save(mapper.toEntity(user));
    }
    
    public void delete(Long id) {
        User user = findExistingUser(id);
        repository.delete(user);
    }
    
    private User findExistingUser(Long id) {
        return repository.findById(id)
                       .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
}
