package com.lsoft.chat.data.dto;

import lombok.Data;

@Data
public class FirebaseRequest {
    private String title;
    private String message;
    private String topic;
    private String token;
}