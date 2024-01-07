package com.example.demo.shared;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserExtendedDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDto toDto(User entity) {
        return UserDto.builder()
                       .id(entity.getId())
                       .userName(entity.getUserName())
                       .profilePicture(entity.getProfilePicture())
                       .build();
    }
    
    public UserExtendedDto toExtendedDto(User entity) {
        return UserExtendedDto.builder()
                       .id(entity.getId())
                       .userName(entity.getUserName())
                       .firstName(entity.getFirstName())
                       .lastName(entity.getLastName())
                       .role(entity.getRole())
                       .lastLogin(entity.getLastLogin())
                       .age(entity.getAge())
                       .profilePicture(entity.getProfilePicture())
                       .build();
    }
    
    public User toEntity(UserExtendedDto dto) {
        return new User().builder()
                   .id(dto.getId())
                   .userName(dto.getUserName())
                   .firstName(dto.getFirstName())
                   .lastName(dto.getLastName())
                   .role(dto.getRole())
                   .lastLogin(dto.getLastLogin())
                   .age(dto.getAge())
                   .profilePicture(dto.getProfilePicture())
                   .build();
    }
    
}
