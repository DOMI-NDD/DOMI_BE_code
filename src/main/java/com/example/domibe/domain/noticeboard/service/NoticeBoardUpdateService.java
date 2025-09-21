package com.example.domibe.domain.noticeboard.service;

import com.example.domibe.domain.auth.exception.AccountNotFoundException;
import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardRequest;
import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.exception.NoticeBoardNotFoundException;
import com.example.domibe.domain.noticeboard.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeBoardUpdateService {

    private final NoticeBoardRepository noticeBoardRepository;
    private final NoticeBoardCreateService noticeBoardCreateService;

    public void execute(NoticeBoardRequest request, Integer id, Authentication authentication) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow((NoticeBoardNotFoundException::new));

        noticeBoard.update(request.getTitle(), request.getDetail(), LocalDateTime.now());
        noticeBoardRepository.save(noticeBoard);

    }

}
