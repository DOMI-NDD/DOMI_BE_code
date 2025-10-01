package com.example.domibe.domain.calendar.service;

import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventService {

  private final CalendarEventRepository calendarRepository;

  public List<CalendarEvent> execute(int year, int month) {
    LocalDate request = LocalDate.of(year, month, 1);

    LocalDate previousMonth = request.minusMonths(1);

    LocalDate nextMonth = request.plusMonths(1).withDayOfMonth(request.plusMonths(1).lengthOfMonth());

    return calendarRepository.findByDateRange(previousMonth, nextMonth);
  }

}
