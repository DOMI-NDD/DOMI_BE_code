package com.example.domibe.domain.noticeboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class NoticeBoardResponse {
    private String title;
    private String detail;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
