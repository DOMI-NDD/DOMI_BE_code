package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardGetListResponse;
import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeBoardGetListService {

  private final NoticeBoardRepository noticeBoardRepository;

  public List<NoticeBoardGetListResponse> execute(String keyword) {
    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    List<NoticeBoard> noticeBoards;

    if (keyword == null || keyword.isEmpty()) {
      noticeBoards = noticeBoardRepository.findAll(sort);
    } else {
      noticeBoards = noticeBoardRepository.findByTitleContainingOrDetailContaining(keyword, keyword, sort);
    }

    return noticeBoards.stream()
        .map(nb -> new NoticeBoardGetListResponse(
            nb.getId(),
            nb.getTitle(),
            nb.getUser().getAccountId(),
            nb.getCreatedAt()
        ))
        .toList();
  }
}

