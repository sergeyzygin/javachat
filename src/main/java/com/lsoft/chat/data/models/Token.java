package com.lsoft.chat.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;
    private String token;
}
