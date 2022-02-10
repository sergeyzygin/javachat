package com.lsoft.chat.controllers;

import com.lsoft.chat.data.dto.FindUserRequest;
import com.lsoft.chat.data.dto.UserResponse;
import com.lsoft.chat.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "find")
    @ResponseBody
    public UserResponse getUserByName(@RequestBody FindUserRequest request){
        UserResponse response = userService.getUserByName(request.getName());
        if (response != null) {
            return response;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
    }

    @GetMapping
    @ResponseBody
    public Map<String, Object> getAllUsers(){
        return userService.getAllUsers();
    }




}
