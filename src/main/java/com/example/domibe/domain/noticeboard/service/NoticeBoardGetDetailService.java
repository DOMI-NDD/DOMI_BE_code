package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardGetDetailResponse;
import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.exception.NoticeBoardNotFoundException;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeBoardGetDetailService {

  private final NoticeBoardRepository noticeBoardRepository;

  public NoticeBoardGetDetailResponse execute(Integer id) {
    NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
        .orElseThrow(NoticeBoardNotFoundException::new);

    return NoticeBoardGetDetailResponse.builder()
        .title(noticeBoard.getTitle())
        .detail(noticeBoard.getDetail())
        .build();
  }
}
