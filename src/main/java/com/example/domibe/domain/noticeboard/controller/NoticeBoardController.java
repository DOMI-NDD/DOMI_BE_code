package com.example.domibe.domain.noticeboard.controller;

import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardCreateRequest;
import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardResponse;
import com.example.domibe.domain.noticeboard.service.NoticeBoardCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice-boards")
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardCreateService noticeBoardCreateService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public NoticeBoardResponse createNoticeBoard(
            @RequestBody @Valid NoticeBoardCreateRequest request,
            Authentication authentication) {

        return noticeBoardCreateService.execute(request,authentication);
    }


}
