package com.healthsync.healthsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthsync.healthsync.model.HealthParameter;
import com.healthsync.healthsync.model.MedicalReport;
import com.healthsync.healthsync.model.User;

@Repository
public interface HealthParameterRepository extends JpaRepository<HealthParameter, Long> {

    List<HealthParameter> findByMedicalReport(MedicalReport report);
    void deleteByMedicalReport(MedicalReport report);
    List<HealthParameter> findByMedicalReportUserAndParameterNameOrderByMedicalReportUploadDateAsc(
        User user, String parameterName);


}
