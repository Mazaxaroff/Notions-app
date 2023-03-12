package com.example.notionsapp;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Notion {
    private UUID id;
    private String title;
    private String text;
    private LocalDateTime createDate;


    public Notion(String title, String text) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
        this.createDate = LocalDateTime.now();
    }

    public Notion(UUID id, String title, String text, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createDate = createDate;
    }
}
