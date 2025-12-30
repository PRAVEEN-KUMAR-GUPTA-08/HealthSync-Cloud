package com.healthsync.healthsync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthsync.healthsync.model.MedicalReferenceRange;

@Repository
public interface MedicalReferenceRangeRepository
        extends JpaRepository<MedicalReferenceRange, Long> {

    Optional<MedicalReferenceRange> findByParameterName(String parameterName);
}
