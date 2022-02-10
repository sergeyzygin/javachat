package com.lsoft.chat.controllers;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.ChatRequest;
import com.lsoft.chat.data.dto.ChatResponse;
import com.lsoft.chat.data.repositories.ChatRepository;
import com.lsoft.chat.services.ChatService;
import com.lsoft.chat.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    AuthService authService;

    @PostMapping
    public ChatResponse createChat(@RequestBody ChatRequest request) {
        return chatService.createChat(request);
    }

    @GetMapping
    public Map getAllChats() {
        java.util.Map<String,Object> chatMap = new HashMap<String, Object>();
        chatMap.put("data", chatService.getAllChats());
        return chatMap;
    }

    @GetMapping(value = "/{chatId}")
    public ChatResponse getChat(@PathVariable int chatId) {
        User u = authService.getCurrentUser();
        return chatService.getChatById(chatId);
    }
}
