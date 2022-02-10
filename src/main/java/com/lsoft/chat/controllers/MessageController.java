package com.lsoft.chat.controllers;

import com.lsoft.chat.data.dto.MessageRequest;
import com.lsoft.chat.data.dto.MessageResponse;
import com.lsoft.chat.services.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    MessageService service;


    @PostMapping
    public MessageResponse createMessage(@RequestBody MessageRequest request){
                MessageResponse response = service.createMessage(request);
                return response;
    }

    @GetMapping(value = "/{chatId}")
    public Map getMessagesForChat(@PathVariable int chatId){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data", service.getMessagesForChat(chatId));

        return map;
    }

    @SendTo("/topic/test")
    public MessageResponse sendMessage(MessageResponse chatMessage) {
        return chatMessage;
    }
}
