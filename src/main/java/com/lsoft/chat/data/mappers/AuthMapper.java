package com.lsoft.chat.data.mappers;

import com.lsoft.chat.config.jwt.JwtProvider;
import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.AuthResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    JwtProvider jwtProvider;


    public AuthResponse toResponse(User user){
        if (user == null) return null;
        AuthResponse response = mapper.map(user, AuthResponse.class);
        String token = jwtProvider.generateToken(user.getUsername());
        response.setToken(token);
        return response;
    }


}
