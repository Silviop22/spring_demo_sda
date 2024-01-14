package com.example.demo.model.ui;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserExtendedDto {
    private Long id;
    @NotNull(message = "Username is mandatory")
    private String userName;
    @NotNull(message = "First name is mandatory")
    private String firstName;
    @NotNull(message = "Last mame is mandatory")
    private String lastName;
    @NotNull(message = "Role is mandatory")
    private Role role;
    private LocalDate lastLogin;
    private Integer age;
    @NotNull(message = "Profile picture is mandatory")
    private String profilePicture;
}
