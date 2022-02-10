package com.lsoft.chat.controllers;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.dto.*;
import com.lsoft.chat.data.mappers.AuthMapper;
import com.lsoft.chat.services.AuthService;
import com.lsoft.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;


    @Autowired
    AuthMapper mapper;

    @PostMapping("/auth")
    @ResponseBody
    public AuthResponse auth(@Valid @RequestBody AuthRequest request, Errors errors){

        if (errors.hasErrors()) {
            return new AuthResponse(new ErrorResponse(522, errors));
        }
        User user = authService.createUser(request.getLogin(), request.getPassword());
        return mapper.toResponse(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestBody AuthRequest request){
        User user = authService.loadUserByUsernameAndPassword(request.getLogin(), request.getPassword());
        return mapper.toResponse(user);
    }

    @Value("${spring.datasource.url}")
    String dbpath;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;
/*
    @GetMapping("/test")
    @ResponseBody
    public String test(){

        return "test passed " + dbpath + ":" + username +" " + password ;
    }
*/
    @PostMapping(path = "check")
    @ResponseBody
    public UserResponse getUserByName(@RequestBody FindUserRequest request){
        UserResponse response = userService.getUserByName(request.getName());
        if (response != null) {
            response.clearPrivateData();
            return response;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
    }



}
