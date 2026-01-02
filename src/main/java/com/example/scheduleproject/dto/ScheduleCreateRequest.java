package com.example.scheduleproject.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    private String title;
    private String content;
    private String writer;
    private String password;
}
