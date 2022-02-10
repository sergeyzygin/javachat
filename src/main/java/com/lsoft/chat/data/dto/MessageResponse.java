package com.lsoft.chat.data.dto;

import com.lsoft.chat.data.models.File;
import lombok.Data;

@Data
public class MessageResponse {
    int id;
    int chatId;
    int owner;
    String text;
    String createdAt;
    long lastUpdated;
    int fileId;
    File file;
}
