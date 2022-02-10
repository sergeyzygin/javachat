package com.lsoft.chat.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse implements Serializable {
    private int id;
    private String username;
    private String token;
    private ErrorResponse error;

    public AuthResponse(int id, String username, String token){
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public AuthResponse(ErrorResponse error){
        this.error = error;
    }
}
