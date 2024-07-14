package com.example.demo.Attendance.repository;

import com.example.demo.Attendance.entity.Attendance;
import com.example.demo.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findByDateAndUser(Date date, User user);

    List<Attendance> findByUser(User user);
}
