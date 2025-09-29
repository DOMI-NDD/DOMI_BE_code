package com.example.domibe.domain.calendar.service;

import com.example.domibe.domain.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEventService {

  private final CalendarEventRepository calendarEvenRepository;

  public void execute(Integer id) {
    calendarEvenRepository.deleteById(id);
  }

}
