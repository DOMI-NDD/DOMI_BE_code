package com.example.domibe.domain.noticeboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class NoticeBoardGetListResponse {
    private Integer id;
    private String title;
    private String writer;
    private LocalDateTime createdAt;
}
