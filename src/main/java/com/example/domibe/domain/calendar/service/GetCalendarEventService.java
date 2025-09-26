package com.example.domibe.domain.calendar.service;

import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCalendarEventService {

  private final CalendarRepository calendarRepository;

  public List<CalendarEvent> execute(int year, int month) {
    return calendarRepository.findByMonthAndYear(year, month);
  }

}
