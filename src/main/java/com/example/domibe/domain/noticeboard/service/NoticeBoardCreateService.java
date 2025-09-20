package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardCreateRequest;
import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardResponse;
import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import com.example.domibe.domain.user.User;
import com.example.domibe.global.security.auth.CustomUserDetails;
import com.zaxxer.hikari.util.UtilityElf;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeBoardCreateService {

    private final NoticeBoardRepository noticeBoardRepository;

    @Transactional
    public NoticeBoardResponse execute(NoticeBoardCreateRequest noticeBoardCreateRequest, Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        NoticeBoard noticeBoard = NoticeBoard.builder()
                .title(noticeBoardCreateRequest.getTitle())
                .detail(noticeBoardCreateRequest.getDetail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        NoticeBoard savedNoticeBoard = noticeBoardRepository.save(noticeBoard);

        return NoticeBoardResponse.builder()
                .title(savedNoticeBoard.getTitle())
                .detail(savedNoticeBoard.getDetail())
                .createdAt(savedNoticeBoard.getCreatedAt())
                .updatedAt(savedNoticeBoard.getUpdatedAt())
                .writer(savedNoticeBoard.getUser().getAccountId())
                .build();
    }

}
