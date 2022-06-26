package com.luchoDev.repairmanager.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class NewUser {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    @Email
    private String email;
    private Set<String> roles = new HashSet<>();
}