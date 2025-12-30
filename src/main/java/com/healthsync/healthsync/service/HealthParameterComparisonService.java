package com.healthsync.healthsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthsync.healthsync.model.HealthParameter;
import com.healthsync.healthsync.repository.HealthParameterRepository;
import com.healthsync.healthsync.repository.MedicalReferenceRangeRepository;

@Service
public class HealthParameterComparisonService {

    private final HealthParameterRepository parameterRepository;
    private final MedicalReferenceRangeRepository rangeRepository;

    public HealthParameterComparisonService(HealthParameterRepository parameterRepository,
                                            MedicalReferenceRangeRepository rangeRepository) {
        this.parameterRepository = parameterRepository;
        this.rangeRepository = rangeRepository;
    }

    public void compareAndUpdateStatus() {

        List<HealthParameter> parameters = parameterRepository.findAll();

        for (HealthParameter param : parameters) {
            rangeRepository.findByParameterName(param.getParameterName())
                .ifPresent(range -> {
                    if (param.getParameterValue() < range.getMinValue()
                        || param.getParameterValue() > range.getMaxValue()) {
                        param.setStatus("ABNORMAL");
                    } else {
                        param.setStatus("NORMAL");
                    }
                    parameterRepository.save(param);
                });
        }
    }
}
