package com.example.demo.Attendance.service;

import com.example.demo.Attendance.entity.Attendance;
import com.example.demo.Attendance.repository.AttendanceRepository;
import com.example.demo.User.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> findById(Integer id) {
        return attendanceRepository.findById(id);
    }

    public Optional<Attendance> findByDateAndUser(Date date, User user) {
        return attendanceRepository.findByDateAndUser(date, user);
    }

    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteById(Integer id) {
        attendanceRepository.deleteById(id);
    }
}
