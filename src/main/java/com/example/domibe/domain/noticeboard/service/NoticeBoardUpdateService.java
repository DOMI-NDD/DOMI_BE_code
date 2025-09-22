package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardRequest;
import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.exception.NoticeBoardNotFoundException;
import com.example.domibe.domain.noticeboard.exception.PostNotOwnerException;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import com.example.domibe.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeBoardUpdateService {

    private final NoticeBoardRepository noticeBoardRepository;

    @Transactional
    public void execute(NoticeBoardRequest request, Integer id, Authentication authentication) {

        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
            .orElseThrow((NoticeBoardNotFoundException::new));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = customUserDetails.getUser().getId();

        if(!userId.equals(noticeBoard.getUser().getId())) {
            throw new PostNotOwnerException();
        }

        noticeBoard.update(request.getTitle(), request.getDetail(), LocalDateTime.now());
        noticeBoardRepository.save(noticeBoard);

    }

}
