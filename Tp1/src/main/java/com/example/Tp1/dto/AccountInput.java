package com.example.Tp1.dto;

import lombok.Data;

@Data
public class AccountInput {
    private String login;
    private String password;
    private String role;
    private Boolean isActive;
    private Long clientId;
}