package com.dailysnapshotbackend.web;

import com.dailysnapshotbackend.dto.NewsItemDTO;
import com.dailysnapshotbackend.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/search")
    public ResponseEntity<List<NewsItemDTO>> search(@RequestParam("q") String query,
                                                    @RequestParam(name = "limit", defaultValue = "10") int limit) {
        return ResponseEntity.ok(this.newsService.search(query, limit));
    }
}
