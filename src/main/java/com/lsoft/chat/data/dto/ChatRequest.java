package com.lsoft.chat.data.dto;

import com.lsoft.chat.data.User;
import lombok.Data;

import java.util.Set;

@Data
public class ChatRequest {
    String name;
    Set<User> users;
}
