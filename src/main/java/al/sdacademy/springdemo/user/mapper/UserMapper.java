package al.sdacademy.springdemo.user.mapper;

import al.sdacademy.springdemo.user.model.User;
import al.sdacademy.springdemo.user.model.UserDto;
import al.sdacademy.springdemo.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto toDto(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        return result;
    }
    
    @Override
    public User toEntity(UserDto userDto) {
        User result = new User();
        result.setId(userDto.getId());
        result.setName(userDto.getName());
        result.setEmail(userDto.getEmail());
        return result;
    }
}
