package com.example.domibe.domain.calendar.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateEventRequest {
  @NotBlank
  private String title;
  @NotBlank
  private String detail;
  @NotNull
  private LocalDate startDate;
  @NotNull
  private LocalDate endDate;

}
