package com.lsoft.chat.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lsoft.chat.data.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;

    @Column(name = "last_updated")
    private long lastUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<User> users;
}
