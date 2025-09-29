package com.example.domibe.domain.calendar.repository;

import com.example.domibe.domain.calendar.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {
  @Query("SELECT e FROM CalendarEvent e WHERE MONTH(e.startDate) = :month AND YEAR(e.startDate) = :year")
  List<CalendarEvent> findByMonthAndYear(@Param("year") int year, @Param("month") int month);

}
