package com.healthsync.healthsync.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthsync.healthsync.dto.TrendPointDTO;
import com.healthsync.healthsync.model.HealthParameter;
import com.healthsync.healthsync.model.User;
import com.healthsync.healthsync.repository.HealthParameterRepository;

@RestController
@RequestMapping("/api/trends")
public class TrendController {

    private final HealthParameterRepository parameterRepository;

    public TrendController(HealthParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    // Temporary user (JWT later)
    private User getDummyUser() {
        User user = new User();
        user.setId(1L);
        return user;
    }

    @GetMapping("/{parameterName}")
    public List<TrendPointDTO> getTrend(@PathVariable String parameterName) {

        List<HealthParameter> params =
                parameterRepository
                        .findByMedicalReportUserAndParameterNameOrderByMedicalReportUploadDateAsc(
                                getDummyUser(), parameterName);

        return params.stream()
                .map(p -> new TrendPointDTO(
                        p.getMedicalReport().getUploadDate(),
                        p.getParameterValue(),
                        p.getStatus()
                ))
                .toList();
    }
}
