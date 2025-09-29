package com.example.domibe.domain.calendar.controller;

import com.example.domibe.domain.calendar.dto.request.CreateEventRequest;
import com.example.domibe.domain.calendar.dto.request.UpdateEventRequest;
import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.service.CreateEventService;
import com.example.domibe.domain.calendar.service.DeleteEventService;
import com.example.domibe.domain.calendar.service.GetEventService;
import com.example.domibe.domain.calendar.service.UpdateEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendars")
public class CalendarController {

  private final CreateEventService createEventService;
  private final GetEventService getEventService;
  private final UpdateEventService updateEventService;
  private final DeleteEventService deleteEventService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createEvent(@RequestBody @Valid CreateEventRequest request) {
    createEventService.execute(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CalendarEvent> getEvents(@RequestParam int year,@RequestParam int month) {
    return getEventService.execute(year,month);

  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateEvent(@PathVariable Integer id, @RequestBody @Valid UpdateEventRequest request) {
    updateEventService.execute(id,request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEvent(@PathVariable Integer id) {
    deleteEventService.execute(id);
  }
}
