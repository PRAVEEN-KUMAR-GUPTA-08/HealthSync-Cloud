package com.healthsync.healthsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthsync.healthsync.model.HealthParameter;
import com.healthsync.healthsync.model.MedicalReport;

@Repository
public interface HealthParameterRepository extends JpaRepository<HealthParameter, Long> {

    List<HealthParameter> findByMedicalReport(MedicalReport report);
    void deleteByMedicalReport(MedicalReport report);

}
