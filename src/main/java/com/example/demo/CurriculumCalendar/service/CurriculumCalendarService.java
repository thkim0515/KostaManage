package com.example.demo.CurriculumCalendar.service;

import com.example.demo.CurriculumCalendar.entity.CurriculumCalendar;
import com.example.demo.CurriculumCalendar.repository.CurriculumCalendarRepository;
import com.example.demo.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CurriculumCalendarService {
    private final CurriculumCalendarRepository curriculumCalendarRepository;
    private final UserRepository userRepository;

    public CurriculumCalendarService(CurriculumCalendarRepository curriculumCalendarRepository, UserRepository userRepository) {
        this.curriculumCalendarRepository = curriculumCalendarRepository;
        this.userRepository = userRepository;
    }

    public CurriculumCalendar createEvent(Integer userId, String title, String description, LocalDate eventDate) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        CurriculumCalendar event = CurriculumCalendar.builder()
                .user(user)
                .title(title)
                .description(description)
                .eventDate(eventDate)
                .build();
        return curriculumCalendarRepository.save(event);
    }

    public Optional<CurriculumCalendar> getEventById(Integer id) {
        return curriculumCalendarRepository.findById(id);
    }

    public List<CurriculumCalendar> getAllEvents() {
        return curriculumCalendarRepository.findAll();
    }

    public CurriculumCalendar updateEvent(Integer id, String title, String description, LocalDate eventDate) {
        var event = curriculumCalendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);

        return curriculumCalendarRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        var event = curriculumCalendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        curriculumCalendarRepository.delete(event);
    }
}
