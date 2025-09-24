package com.example.domibe.domain.noticeboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
@Builder
public class NoticeBoardGetDetailResponse {
  private String title;
  private String detail;
}
