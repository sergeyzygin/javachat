package com.lsoft.chat.services;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.UserResponse;
import com.lsoft.chat.data.mappers.UserMapper;
import com.lsoft.chat.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper mapper;

    public UserResponse getUserByName(String username){
        User user = userRepository.findByUsername(username);
        return mapper.toResponse(user);
    }

    public Map<String,Object> getAllUsers(){
        List<UserResponse> response = new ArrayList<>();
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> {
            response.add(mapper.toResponse(user));
        });
        Map<String,Object> userMap = new HashMap<String, Object>();
        userMap.put("data", response);
        return userMap;
    }

}
