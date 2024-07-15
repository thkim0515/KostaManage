package com.example.demo.Attendance.controller;

import com.example.demo.Attendance.entity.Attendance;
import com.example.demo.Attendance.service.AttendanceService;
import com.example.demo.User.entity.User;
import com.example.demo.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Attendance> getAttendanceById(@PathVariable Integer id) {
        return attendanceService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Attendance> getAttendanceByUserId(@PathVariable Integer userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(attendanceService::findByUser).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        Optional<Attendance> existingAttendance = attendanceService.findByDateAndUser(attendance.getDate(), attendance.getUser());
        if (existingAttendance.isPresent()) {
            Attendance existing = existingAttendance.get();
            existing.setStatus(attendance.getStatus());
            return attendanceService.save(existing);
        } else {
            return attendanceService.save(attendance);
        }
    }

    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Integer id, @RequestBody Attendance updatedAttendance) {
        return attendanceService.findById(id)
                .map(attendance -> {
                    attendance.setUser(updatedAttendance.getUser());
                    attendance.setCohort(updatedAttendance.getCohort());
                    attendance.setDate(updatedAttendance.getDate());
                    attendance.setStatus(updatedAttendance.getStatus());
                    attendance.setIsDelete(updatedAttendance.getIsDelete());
                    return attendanceService.save(attendance);
                })
                .orElseGet(() -> {
                    updatedAttendance.setAttendanceId(id);
                    return attendanceService.save(updatedAttendance);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteById(id);
    }
}