package com.lsoft.chat.data.mappers;

import com.lsoft.chat.data.dto.ChatRequest;
import com.lsoft.chat.data.dto.ChatResponse;
import com.lsoft.chat.data.models.Chat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ChatMapper {
    @Autowired
    private ModelMapper mapper;

    public Chat toEntity(ChatRequest chatRequest) {
        return Objects.isNull(chatRequest)? null : mapper.map(chatRequest, Chat.class);
    }

    public ChatResponse toResponse(Chat chat) {
        return Objects.isNull(chat)? null : mapper.map(chat, ChatResponse.class);
    }
}
