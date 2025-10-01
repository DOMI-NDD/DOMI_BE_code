package com.example.domibe.domain.calendar.repository;

import com.example.domibe.domain.calendar.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {
  @Query("SELECT e FROM CalendarEvent e " + "WHERE e.startDate BETWEEN :start AND :end")
  List<CalendarEvent> findByDateRange(LocalDate start, LocalDate end);


}
