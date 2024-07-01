package com.example.demo.Cohort.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cohorts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cohort_id")
    private Integer cohortId;

    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @Column(name = "branch_location", nullable = false)
    private String branchLocation;

    @Column(name = "generation", nullable = false)
    private String generation;

    @Column(name = "cohort_number", nullable = false)
    private Integer cohortNumber;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
}
