package com.example.demo.CurriculumCalendar.controller;

import com.example.demo.CurriculumCalendar.entity.CurriculumCalendar;
import com.example.demo.CurriculumCalendar.service.CurriculumCalendarService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendar")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CurriculumCalendarController {

    private final CurriculumCalendarService curriculumCalendarService;

    public CurriculumCalendarController(CurriculumCalendarService curriculumCalendarService) {
        this.curriculumCalendarService = curriculumCalendarService;
    }

    // 일정 등록
    @PostMapping("/create")
    public CurriculumCalendar createEvent(@RequestParam Integer userId, @RequestParam String title, @RequestParam String description, @RequestParam LocalDate eventDate) {
        return curriculumCalendarService.createEvent(userId, title, description, eventDate);
    }

    // 일정 조회
    @GetMapping("/get/{id}")
    public Optional<CurriculumCalendar> getEventById(@PathVariable Integer id) {
        return curriculumCalendarService.getEventById(id);
    }

    // 모든 일정 조회
    @GetMapping("/all")
    public List<CurriculumCalendar> getAllEvents() {
        return curriculumCalendarService.getAllEvents();
    }

    // 일정 수정
    @PutMapping("/update/{id}")
    public CurriculumCalendar updateEvent(@PathVariable Integer id, @RequestParam String title, @RequestParam String description, @RequestParam LocalDate eventDate) {
        return curriculumCalendarService.updateEvent(id, title, description, eventDate);
    }

    // 일정 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        curriculumCalendarService.deleteEvent(id);
    }
}
