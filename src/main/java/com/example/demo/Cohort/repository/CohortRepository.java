package com.example.demo.Cohort.repository;

import com.example.demo.Cohort.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {

    @Query("SELECT c FROM Cohort c WHERE c.isDeleted = false")
    List<Cohort> findAllActive();
}
