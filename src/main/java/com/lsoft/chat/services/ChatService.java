package com.lsoft.chat.services;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.ChatRequest;
import com.lsoft.chat.data.dto.ChatResponse;
import com.lsoft.chat.data.dto.MessageResponse;
import com.lsoft.chat.data.mappers.ChatMapper;
import com.lsoft.chat.data.models.Chat;
import com.lsoft.chat.data.models.Message;
import com.lsoft.chat.data.repositories.ChatRepository;
import com.lsoft.chat.utils.LastUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ChatService {

    @Autowired
    private ChatRepository repository;

    @Autowired
    private ChatMapper mapper;

    @Autowired
    private AuthService authService;

    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messageSender;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public ChatResponse createChat(ChatRequest request) {
        Chat chat = mapper.toEntity(request);
        this.updateLastUpdate(chat);
        chat = repository.save(chat);
        addCurrentUserToChat(chat);
        repository.save(chat);

        Optional<Chat> result = repository.findById(chat.getId());
        return result.map(value -> mapper.toResponse(value)).orElse(null);
    }

    public ChatResponse getChatById(int chatId) {
        return repository.findById(chatId).map(value -> mapper.toResponse(value)).orElse(null);
    }

    public List<ChatResponse> getAllChats() {
        List<ChatResponse> response = new ArrayList<>();
        User u = authService.getCurrentUser();
        Iterable<Chat> chats =  repository.findByUsers_id(u.getId(), Sort.by("lastUpdated").descending());
        chats.forEach(chat -> {
            Message message = messageService.lastMessage(chat.getId());
            ChatResponse chatResponse = mapper.toResponse(chat);
            chatResponse.setLastMessage(message);
            response.add(chatResponse);
        });
        return response;
    }

    private void addCurrentUserToChat(Chat chat){
        AtomicBoolean userAdded = new AtomicBoolean(false);
        User currentUser = authService.getCurrentUser();
        chat.getUsers().forEach(user -> {
            if(currentUser.getId() == user.getId()) userAdded.set(true);
        });
        if (!userAdded.get()) chat.getUsers().add(currentUser);
    }

    public void updateLastUpdate(int chat_id){
        Optional<Chat> mchat = repository.findById(chat_id);
        if (mchat.isPresent()){
            Chat chat = mchat.get();
            this.updateLastUpdate(chat);
            repository.save(chat);
        }
    }
    private void updateLastUpdate(Chat chat){
        chat.setLastUpdated(LastUpdate.getCurrentLong());
    }

    public void sendToUsers(int chatId, MessageResponse response) {
        Optional<Chat> mchat = repository.findById(chatId);
        if (mchat.isPresent()){
            Set<User> users = mchat.get().getUsers();
            users.forEach(user -> {
                messageSender.convertAndSendToUser(user.getUsername(), "/queue/chat", response);
            });


        }
    }
}
