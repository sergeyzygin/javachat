package com.lsoft.chat.data.dto;

import lombok.Data;

@Data
public class MessageRequest {
    int chatId;
    String text;
    int fileId;
}
