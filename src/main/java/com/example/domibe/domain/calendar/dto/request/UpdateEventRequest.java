package com.example.domibe.domain.calendar.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateEventRequest {

  @NotBlank
  private String title;
  private String detail;
}
