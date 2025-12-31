package com.healthsync.healthsync.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthsync.healthsync.dto.HealthParameterDTO;
import com.healthsync.healthsync.dto.HistoryResponseDTO;
import com.healthsync.healthsync.model.MedicalReport;
import com.healthsync.healthsync.model.User;
import com.healthsync.healthsync.repository.MedicalReportRepository;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final MedicalReportRepository reportRepository;

    public HistoryController(MedicalReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // TEMP user (JWT later)
    private User getDummyUser() {
        User user = new User();
        user.setId(1L);
        return user;
    }

   @GetMapping
public List<HistoryResponseDTO> getUserHistory() {

    User user = getDummyUser();
    List<MedicalReport> reports =
            reportRepository.findByUserOrderByUploadDateAsc(user);

    return reports.stream().map(report -> {

        List<HealthParameterDTO> params =
                report.getHealthParameters()
                        .stream()
                        .map(p -> new HealthParameterDTO(
                                p.getParameterName(),
                                p.getParameterValue(),
                                p.getUnit(),
                                p.getStatus()
                        ))
                        .toList();

        return new HistoryResponseDTO(
                report.getId(),
                report.getFileName(),
                report.getUploadDate(),
                params
        );

    }).toList();
}

}
