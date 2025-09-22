package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.exception.NoticeBoardNotFoundException;
import com.example.domibe.domain.noticeboard.exception.PostNotOwnerException;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import com.example.domibe.global.security.auth.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeBoardDeleteService {
  private final NoticeBoardRepository noticeBoardRepository;

  @Transactional
  public void execute(Integer id, Authentication authentication) {

    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    Integer userId = customUserDetails.getUser().getId();

    NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
        .orElseThrow(NoticeBoardNotFoundException::new);
    if(!userId.equals(noticeBoard.getUser().getId())){
      throw new PostNotOwnerException();
    }
    noticeBoardRepository.deleteById(id);
  }
}
