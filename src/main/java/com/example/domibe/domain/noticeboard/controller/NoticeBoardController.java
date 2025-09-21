package com.example.domibe.domain.noticeboard.controller;

import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardRequest;
import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardResponse;
import com.example.domibe.domain.noticeboard.service.NoticeBoardCreateService;
import com.example.domibe.domain.noticeboard.service.NoticeBoardUpdateService;
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
    private final NoticeBoardUpdateService noticeBoardUpdateService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public NoticeBoardResponse createNoticeBoard(
            @RequestBody @Valid NoticeBoardRequest request,
            Authentication authentication) {

        return noticeBoardCreateService.execute(request,authentication);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNoticeBoard(@PathVariable Integer id, @RequestBody @Valid NoticeBoardRequest request, Authentication authentication) {
         noticeBoardUpdateService.execute(request,id,authentication);
    }



}
