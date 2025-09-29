package com.example.domibe.domain.calendar.entity;

import com.example.domibe.domain.calendar.dto.request.UpdateEventRequest;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String title;

  private String detail;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  public void UpdateEvent(@Valid UpdateEventRequest request) {
    this.title = request.getTitle();
    this.detail = request.getDetail();
  }
}
