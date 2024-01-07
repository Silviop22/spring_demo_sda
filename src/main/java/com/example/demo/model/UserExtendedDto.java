package com.example.demo.model;

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
    private String firstName;
    private String lastName;
    private Role role;
    private LocalDate lastLogin;
    private Integer age;
    private String profilePicture;
}
