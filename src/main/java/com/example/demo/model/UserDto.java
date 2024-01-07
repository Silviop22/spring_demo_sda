package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String profilePicture;
}
