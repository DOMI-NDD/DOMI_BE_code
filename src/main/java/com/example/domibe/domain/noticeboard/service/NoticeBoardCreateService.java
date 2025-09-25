package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardRequest;
import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import com.example.domibe.domain.user.User;
import com.example.domibe.global.security.auth.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeBoardCreateService {

    private final NoticeBoardRepository noticeBoardRepository;

    @Transactional
    public void execute(NoticeBoardRequest noticeBoardCreateRequest, Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        NoticeBoard noticeBoard = NoticeBoard.builder()
                .title(noticeBoardCreateRequest.getTitle())
                .detail(noticeBoardCreateRequest.getDetail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        noticeBoardRepository.save(noticeBoard);
    }

}
