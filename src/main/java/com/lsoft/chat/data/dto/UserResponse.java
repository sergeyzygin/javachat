package com.lsoft.chat.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    private int id;
    private String username;

    public void clearPrivateData(){
        this.id = -100;
    }

}

