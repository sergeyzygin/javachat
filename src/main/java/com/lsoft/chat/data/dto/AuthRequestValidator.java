package com.lsoft.chat.data.dto;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class AuthRequestValidator {
    void validateInput(@Valid AuthRequest input){
        // do something
    }
}
