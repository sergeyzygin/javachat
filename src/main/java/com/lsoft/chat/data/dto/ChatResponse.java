package com.lsoft.chat.data.dto;

import com.lsoft.chat.data.models.Message;
import lombok.Data;

import java.util.Set;

@Data
public class ChatResponse {
    private int id;
    private String name;
    Set<UserResponse> users;
    Message lastMessage;
    private long lastUpdated;
}
