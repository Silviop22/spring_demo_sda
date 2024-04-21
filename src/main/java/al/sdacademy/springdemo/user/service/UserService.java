package al.sdacademy.springdemo.user.service;

import al.sdacademy.springdemo.user.mapper.UserMapper;
import al.sdacademy.springdemo.user.model.UserDto;
import al.sdacademy.springdemo.user.model.User;
import al.sdacademy.springdemo.user.repository.UserRepository;
import al.sdacademy.springdemo.commons.util.ObjectPatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserService(UserRepository repository, UserMapper userMapper) {
        this.userRepository = repository;
        this.userMapper = userMapper;
    }
    
    public long count() {
        return userRepository.count();
    }
    
    public Long save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return user.getId();
    }
    
    public void update(UserDto userDto, Long id) {
        User existing = getExistingEntity(id);
        ObjectPatcher.patchObject(userDto, existing);
        userRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        getExistingEntity(id);
        userRepository.deleteById(id);
    }
    
    public List<UserDto> findAll() {
        return userRepository.findAll()
                       .stream()
                       .map(userMapper::toDto)
                       .toList();
    }
    
    public UserDto getById(Long id) {
        return userMapper.toDto(getExistingEntity(id));
    }
    
    private User getExistingEntity(Long id) {
        return userRepository.findById(id)
                       .orElseThrow();
    }
}
