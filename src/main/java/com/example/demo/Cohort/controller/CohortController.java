package com.example.demo.Cohort.controller;

import com.example.demo.Cohort.entity.Cohort;
import com.example.demo.Cohort.service.CohortService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cohorts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CohortController {
    private final CohortService cohortService;

    public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    // Cohort 생성
    @PostMapping("/create")
    public Cohort createCohort(@RequestBody Cohort cohort) {
        return cohortService.createCohort(cohort);
    }

    // 특정 Cohort 조회
    @GetMapping("/get/{id}")
    public Optional<Cohort> getCohortById(@PathVariable Integer id) {
        return cohortService.getCohortById(id);
    }

    // 모든 Cohort 조회
    @GetMapping("/all")
    public List<Cohort> getAllCohorts() {
        return cohortService.getAllCohorts();
    }

    // Cohort 수정
    @PutMapping("/update/{id}")
    public Cohort updateCohort(@PathVariable Integer id, @RequestBody Cohort cohort) {
        return cohortService.updateCohort(id, cohort);
    }

    // Cohort 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteCohort(@PathVariable Integer id) {
        cohortService.deleteCohort(id);
    }
}
