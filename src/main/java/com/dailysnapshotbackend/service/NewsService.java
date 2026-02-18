package com.dailysnapshotbackend.service;

import com.dailysnapshotbackend.dto.NewsItemDTO;

import java.util.List;

public interface NewsService {
    List<NewsItemDTO> search(String query, int limit);
}
