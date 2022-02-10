package com.lsoft.chat.data.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int owner;
    private String name;
    private String ext;
    private String awskey;
    private String type;
    private int size;
}
