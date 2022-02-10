package com.lsoft.chat.data.mappers;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    public UserResponse toResponse(User user){
        return Objects.isNull(user)? null : mapper.map(user, UserResponse.class);
    }
}
