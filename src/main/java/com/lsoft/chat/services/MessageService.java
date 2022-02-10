package com.lsoft.chat.services;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.MessageRequest;
import com.lsoft.chat.data.dto.MessageResponse;
import com.lsoft.chat.data.mappers.MessageMapper;
import com.lsoft.chat.data.models.Message;
import com.lsoft.chat.data.repositories.MessageRepository;
import com.lsoft.chat.utils.LastUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private AuthService authService;

    private ChatService chatService;

    @Autowired
    public void setChatService(@Lazy ChatService chatService) {
        this.chatService = chatService;
    }

    public MessageResponse createMessage(MessageRequest request){
        Message message = messageMapper.toEntity(request);
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        message.setCreatedAt(LastUpdate.getCurrentDate());
        message.setLastUpdated(LastUpdate.getCurrentLong());
        User currentUser = authService.getCurrentUser();
        message.setOwner(currentUser.getId());
        chatService.updateLastUpdate(message.getChatId());
        MessageResponse response = messageMapper.toResponse(messageRepository.save(message));
        chatService.sendToUsers(message.getChatId(), response);
        return response;
    }


    public Message lastMessage(int chatId){
        List<Message> messages = messageRepository.findByChatId(chatId, Sort.by("lastUpdated").descending());
        if ((messages != null) && (messages.size()>0)) return messages.get(0);
        return null;
    }

    public List<MessageResponse> getMessagesForChat(int chatId) {
       List<Message> messages =  messageRepository.findByChatId(chatId, Sort.by("lastUpdated").descending());
       List<MessageResponse> response = messages
                .stream()
                .map(message -> messageMapper.toResponse(message))
                .collect(Collectors.toList());
       return response;
    }
}
