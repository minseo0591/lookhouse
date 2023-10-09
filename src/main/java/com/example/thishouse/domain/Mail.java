package com.example.thishouse.domain;

import lombok.Data;

@Data
public class Mail {
    private String receiver;
    private String title;
    private String content;
}
