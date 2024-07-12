package com.example.demo.Cohort.service;

import com.example.demo.Cohort.entity.Cohort;
import com.example.demo.Cohort.repository.CohortRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CohortService {
    private final CohortRepository cohortRepository;

    public CohortService(CohortRepository cohortRepository) {
        this.cohortRepository = cohortRepository;
    }

    public Cohort createCohort(Cohort cohort) {
        return cohortRepository.save(cohort);
    }

    public Optional<Cohort> getCohortById(Integer id) {
        return cohortRepository.findById(id);
    }

    public List<Cohort> getAllCohorts() {
        return cohortRepository.findAllActive();
    }

    public Cohort updateCohort(Integer id, Cohort updatedCohort) {
        Optional<Cohort> cohortOpt = cohortRepository.findById(id);
        if (cohortOpt.isPresent()) {
            Cohort cohort = cohortOpt.get();
            cohort.setBranchName(updatedCohort.getBranchName());
            cohort.setBranchLocation(updatedCohort.getBranchLocation());
            cohort.setGeneration(updatedCohort.getGeneration());
            cohort.setCohortNumber(updatedCohort.getCohortNumber());
            cohort.setStartDate(updatedCohort.getStartDate());
            cohort.setEndDate(updatedCohort.getEndDate());
            return cohortRepository.save(cohort);
        } else {
            throw new IllegalArgumentException("Cohort not found.");
        }
    }

    public void deleteCohort(Integer id) {
        Optional<Cohort> cohortOpt = cohortRepository.findById(id);
        cohortOpt.ifPresent(cohortRepository::delete);
    }

    public void softDeleteCohort(Integer id) {
        Optional<Cohort> cohortOpt = cohortRepository.findById(id);
        if (cohortOpt.isPresent()) {
            Cohort cohort = cohortOpt.get();
            cohort.setIsDeleted(true);
            cohortRepository.save(cohort);
        } else {
            throw new IllegalArgumentException("Cohort not found.");
        }
    }
}
