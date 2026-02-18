package com.dailysnapshotbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewsItemDTO {
    private String title;
    private String link;
    private String source;
    private String publishedAt;
}
