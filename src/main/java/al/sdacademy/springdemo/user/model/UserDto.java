package al.sdacademy.springdemo.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    
    @NotBlank(message = "First name is mandatory",
            groups = UserCreationValidation.class)
    private String firstName;
    
    @NotBlank(message = "Last name is mandatory",
            groups = UserCreationValidation.class)
    private String lastName;
    
    
    @Email(message = "Email is not valid",
            groups = UserCreationValidation.class)
    private String email;
    
    @Size(min = 8, max = 20)
    @NotBlank(message = "Password is mandatory",
            groups = UserCreationValidation.class)
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
    private UserRole role;
    
}
