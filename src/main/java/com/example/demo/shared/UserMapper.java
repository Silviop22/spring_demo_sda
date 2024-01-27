package com.example.demo.shared;

import com.example.demo.model.User;
import com.example.demo.model.ui.UserDto;
import com.example.demo.model.ui.UserExtendedDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, UserExtendedDto, User> {
    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                       .id(entity.getId())
                       .userName(entity.getUserName())
                       .profilePicture(entity.getProfilePicture())
                       .build();
    }
    
    @Override
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
    
    @Override
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
