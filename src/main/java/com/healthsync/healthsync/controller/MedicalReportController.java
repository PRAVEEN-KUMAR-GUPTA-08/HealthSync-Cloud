package com.healthsync.healthsync.controller;

import com.healthsync.healthsync.model.User;
import com.healthsync.healthsync.service.MedicalReportService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reports")
public class MedicalReportController {

    private final MedicalReportService reportService;

    public MedicalReportController(MedicalReportService reportService) {
        this.reportService = reportService;
    }

    // TEMPORARY user (JWT will replace this later)
    private User getDummyUser() {
        User user = new User();
        user.setId(1L); // must exist in DB
        return user;
    }

    @PostMapping("/upload")
    public String uploadReport(@RequestParam("file") MultipartFile file) {
        try {
            reportService.uploadReport(file, getDummyUser());
            return "Medical report uploaded successfully";
        } catch (Exception e) {
            return "Upload failed: " + e.getMessage();
        }
    }
}
