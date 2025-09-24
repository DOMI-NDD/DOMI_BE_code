package com.example.domibe.domain.noticeboard.controller;

import com.example.domibe.domain.noticeboard.dto.request.NoticeBoardRequest;
import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardGetDetailResponse;
import com.example.domibe.domain.noticeboard.dto.response.NoticeBoardGetListResponse;
import com.example.domibe.domain.noticeboard.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice-boards")
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardCreateService noticeBoardCreateService;
    private final NoticeBoardUpdateService noticeBoardUpdateService;
    private final NoticeBoardDeleteService noticeBoardDeleteService;
    private final NoticeBoardGetListService noticeBoardGetListService;
    private final NoticeBoardGetDetailService noticeBoardGetDetailService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNoticeBoard(
            @RequestBody @Valid NoticeBoardRequest request,
            Authentication authentication) {

         noticeBoardCreateService.execute(request,authentication);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNoticeBoard(@PathVariable Integer id, @RequestBody @Valid NoticeBoardRequest request, Authentication authentication) {
         noticeBoardUpdateService.execute(request,id,authentication);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNoticeBoard(@PathVariable Integer id,Authentication authentication) {
        noticeBoardDeleteService.execute(id,authentication);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<NoticeBoardGetListResponse> getNoticeBoards(@RequestParam(value = "keyword",required = false) String keyword) {
        return noticeBoardGetListService.execute(keyword);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoticeBoardGetDetailResponse getNoticeBoardDetail(@PathVariable Integer id) {
        return noticeBoardGetDetailService.execute(id);
    }




}
