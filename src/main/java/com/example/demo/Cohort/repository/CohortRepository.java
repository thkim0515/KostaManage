package com.example.demo.Cohort.repository;

import com.example.demo.Cohort.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortRepository extends JpaRepository<Cohort, Integer> {
}
