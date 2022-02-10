package com.lsoft.chat.data.mappers;

import com.lsoft.chat.data.dto.MessageRequest;
import com.lsoft.chat.data.dto.MessageResponse;
import com.lsoft.chat.data.models.File;
import com.lsoft.chat.data.models.Message;
import com.lsoft.chat.data.repositories.FileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class MessageMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FileRepository fileRepository;

    public Message toEntity(MessageRequest messageRequest) {
        return Objects.isNull(messageRequest)? null : mapper.map(messageRequest, Message.class);
    }

    public MessageResponse toResponse(Message message) {
        MessageResponse response = Objects.isNull(message)? null : mapper.map(message, MessageResponse.class);
        if ((message != null) && (response != null) && (message.getFileId() != 0)) {
            Optional<File> file = fileRepository.findById(message.getFileId());
            if (file.isPresent())
                response.setFile(file.get());
        }
        return response;
    }
}
