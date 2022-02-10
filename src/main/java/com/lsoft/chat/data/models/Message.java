package com.lsoft.chat.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "chat_id", nullable = false)
    private int chatId;
    @Column(name = "owner", nullable = false)
    private int owner;
    private String text;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "last_updated")
    private long lastUpdated;
    @Column(name = "file_id")
    private int fileId;
}
