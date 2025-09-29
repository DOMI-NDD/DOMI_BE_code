package com.example.domibe.domain.calendar.service;

import com.example.domibe.domain.calendar.dto.request.CreateEventRequest;
import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateEventService {

  private final CalendarEventRepository calendarRepository;

  @Transactional
  public void execute(CreateEventRequest request) {
    CalendarEvent calendar = CalendarEvent.builder()
        .title(request.getTitle())
        .detail(request.getDetail())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .build();
    calendarRepository.save(calendar);
  }

}
