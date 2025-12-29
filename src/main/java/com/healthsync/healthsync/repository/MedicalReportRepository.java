package com.healthsync.healthsync.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthsync.healthsync.model.MedicalReport;
import com.healthsync.healthsync.model.User;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {

    // Fetch all reports uploaded by a specific user
    List<MedicalReport> findByUser(User user);
}
