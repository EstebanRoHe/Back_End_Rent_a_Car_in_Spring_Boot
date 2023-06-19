package com.rent_a_car.Controllers.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String name;
    private String lastName;
    private String username;
    private String password;
    @Email
    private String email;
}
/*
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String name;
    private String lastName;
    private String username;
    private String password;
    @Email
    private String email;
    private Set<String> roles;
}
 */