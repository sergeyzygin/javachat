package com.lsoft.chat.data.dto;


import lombok.Data;
import org.springframework.validation.Errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse implements Serializable {
    int code;
    List<String> messages;

    public ErrorResponse(int code, Errors errors){
        messages = new ArrayList<>();
        if (errors.hasErrors()){
            errors.getAllErrors().forEach(e -> messages.add(e.getDefaultMessage()));
        }
        this.code = code;
    }
}
