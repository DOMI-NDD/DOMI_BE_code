package com.example.domibe.domain.noticeboard.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NoticeBoardRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String detail;

}
