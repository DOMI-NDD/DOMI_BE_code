package com.example.domibe.domain.calendar.controller;

import com.example.domibe.domain.calendar.dto.request.CreateEventRequest;
import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.service.CreateCalendarEventService;
import com.example.domibe.domain.calendar.service.GetCalendarEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendars")
public class CalendarController {

  private final CreateCalendarEventService createCalendarEventService;
  private final GetCalendarEventService getCalendarEventService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createEvent(@RequestBody @Valid CreateEventRequest request) {
    createCalendarEventService.execute(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CalendarEvent> getEvents(@RequestParam int year,@RequestParam int month) {
    return getCalendarEventService.execute(year,month);

  }
}
