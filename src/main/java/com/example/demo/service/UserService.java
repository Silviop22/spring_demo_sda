package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                       .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
    
    public Set<User> getList() {
        return repository.findAll()
                       .stream()
                       .collect(Collectors.toSet());
    }

    public User createUser(User user) {
        Optional<User> existingUser = repository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new EntityExistsException("User with username " + user.getUserName() + " already exists");
        }
        return repository.save(user);
    }

    public void delete(Long id) {
        User user = getUserById(id);
        repository.delete(user);
    }
}
