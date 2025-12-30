package com.healthsync.healthsync.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.healthsync.healthsync.model.MedicalReport;
import com.healthsync.healthsync.model.User;
import com.healthsync.healthsync.repository.MedicalReportRepository;

import net.sourceforge.tess4j.TesseractException;


@Service
public class MedicalReportService {
    

    private final MedicalReportRepository reportRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final OcrService ocrService;
    private final HealthParameterExtractionService extractionService;
    private final HealthParameterComparisonService comparisonService;



   public MedicalReportService(MedicalReportRepository reportRepository,OcrService ocrService,HealthParameterExtractionService extractionService,HealthParameterComparisonService comparisonService) {
            this.reportRepository = reportRepository;
            this.ocrService = ocrService;
            this.extractionService = extractionService;
            this.comparisonService = comparisonService;
    }




    public MedicalReport uploadReport(MultipartFile file, User user) throws IOException {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destinationFile = new File(uploadDir + File.separator + fileName);

        file.transferTo(destinationFile);
        
        String extractedText = "";

        try {
            extractedText = ocrService.extractText(destinationFile);
        } catch (TesseractException e) {
                 // OCR failed – log and continue
            extractedText = "OCR extraction failed";
            e.printStackTrace();
        }

        MedicalReport report = new MedicalReport();
            report.setFileName(fileName);
            report.setFileType(file.getContentType());
            report.setFilePath(destinationFile.getAbsolutePath());
            report.setExtractedText(extractedText);
            report.setUploadDate(LocalDateTime.now());
            report.setUser(user);


            MedicalReport savedReport = reportRepository.save(report);
            extractionService.extractParameters(savedReport);
            comparisonService.compareAndUpdateStatus();

        return savedReport;

    }
}
