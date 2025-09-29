package com.example.domibe.domain.calendar.service;

import com.example.domibe.domain.calendar.dto.request.UpdateEventRequest;
import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.exception.NotFoundEventException;
import com.example.domibe.domain.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UpdateEventService {

  private final CalendarEventRepository calendarEvenRepository;

  public void execute(Integer id, UpdateEventRequest request) {
    CalendarEvent calendarEvent = calendarEvenRepository.findById(id)
        .orElseThrow(NotFoundEventException::new);

    calendarEvent.UpdateEvent(request);
    calendarEvenRepository.save(calendarEvent);

  }
}
