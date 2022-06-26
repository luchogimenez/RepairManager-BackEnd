package com.luchoDev.repairmanager.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {
    @NotBlank
    private String password;
    @NotBlank
    private String username;
}
