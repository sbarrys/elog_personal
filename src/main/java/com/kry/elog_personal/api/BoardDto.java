package com.kry.elog_personal.api;

import lombok.Data;

@Data
public class BoardDto {
    private String writer;
    private String content;
    private String title;
}
