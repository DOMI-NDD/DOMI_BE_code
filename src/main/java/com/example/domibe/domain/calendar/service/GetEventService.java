package com.example.domibe.domain.calendar.service;

import com.example.domibe.domain.calendar.entity.CalendarEvent;
import com.example.domibe.domain.calendar.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetEventService {

  private final CalendarEventRepository calendarRepository;

  @Transactional(readOnly = true)
  public List<CalendarEvent> execute(int year, int month) {
    YearMonth current = YearMonth.of(year, month);
    YearMonth prev = current.minusMonths(1);
    YearMonth next = current.plusMonths(1);

    LocalDate startDate = prev.atDay(1);
    LocalDate endDate = next.atEndOfMonth();

    List<CalendarEvent> dbEvents = calendarRepository.findByDateRange(startDate, endDate);

    List<CalendarEvent> schoolEvents = new ArrayList<>();
    schoolEvents.addAll(crawlMonth(prev.getYear(), prev.getMonthValue()));
    schoolEvents.addAll(crawlMonth(current.getYear(), current.getMonthValue()));
    schoolEvents.addAll(crawlMonth(next.getYear(), next.getMonthValue()));

    schoolEvents = schoolEvents.stream()
            .filter(e -> !e.getStartDate().isBefore(startDate)
                    && !e.getStartDate().isAfter(endDate))
            .collect(Collectors.toList());

    List<CalendarEvent> result = new ArrayList<>();
    result.addAll(dbEvents);
    result.addAll(schoolEvents);

    return result;
  }

  private List<CalendarEvent> crawlMonth(int year, int month) {
    List<CalendarEvent> events = new ArrayList<>();
    String monthStr = String.format("%02d", month);
    String url = String.format(
            "https://dsmhs.djsch.kr/schedule/list.do?s=dsmhs&schdYear=%d&schdMonth=%s",
            year, monthStr
    );

    try {
      Document document = Jsoup.connect(url)
              .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
              .referrer("https://dsmhs.djsch.kr/")
              .timeout(10000)
              .get();

      Elements days = document.select("table.tb_calendar tbody tr td");

      for (Element day : days) {
        String dayText = day.ownText().replaceAll("[^0-9]", "").trim();

        if (dayText.isEmpty() || !dayText.matches("\\d+")) {
          continue;
        }

        int dayNum = Integer.parseInt(dayText);
        LocalDate eventDate = LocalDate.of(year, month, dayNum);
        Elements eventElements = day.select("ul li a");

        for (Element event : eventElements) {
          String eventName = event.ownText().trim();

          CalendarEvent calendarEvent = CalendarEvent.builder()
                  .title(eventName)
                  .detail("학교 캘린더 일정입니다")
                  .startDate(eventDate)
                  .endDate(eventDate)
                  .build();

          events.add(calendarEvent);
        }
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return events;
  }
}