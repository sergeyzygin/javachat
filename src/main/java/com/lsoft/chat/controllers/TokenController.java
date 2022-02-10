package com.lsoft.chat.controllers;

import com.lsoft.chat.data.dto.TokenRequest;
import com.lsoft.chat.services.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/user/token")
public class TokenController {
    @Autowired
    TokenService service;

    @PostMapping
    public ResponseEntity createToken(@RequestBody TokenRequest request){
        if (service.addToken(request.getToken())) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }

}
