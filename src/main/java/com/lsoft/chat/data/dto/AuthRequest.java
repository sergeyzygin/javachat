package com.lsoft.chat.data.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
    @NotEmpty(message = "Please provide a name")
    private String login;
    @NotEmpty(message = "Please provide a password")
    private String password;
}
