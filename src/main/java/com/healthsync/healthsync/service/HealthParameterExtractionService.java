package com.healthsync.healthsync.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.healthsync.healthsync.model.HealthParameter;
import com.healthsync.healthsync.model.MedicalReport;
import com.healthsync.healthsync.repository.HealthParameterRepository;

@Service
public class HealthParameterExtractionService {

    private final HealthParameterRepository parameterRepository;

    public HealthParameterExtractionService(HealthParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    public void extractParameters(MedicalReport report) {

        parameterRepository.deleteByMedicalReport(report);

        String text = report.getExtractedText();
        if (text == null || text.isEmpty()) {
            return;
        }
        text = text.replace("\n", " ").replace("\r", " ");


            // Blood Sugar
            extractAndSave(
                "Blood Sugar",
                "mg/dL",
                "(?i)blood\\s*sugar\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)",
                text,
                report
            );

            // Hemoglobin (Hb)
            extractAndSave(
                "Hemoglobin",
                "g/dL",
                "(?i)(hemoglobin|hb)\\s*(\\(hb\\))?\\s*[:=]?\\s*(\\d+(?:\\.\\d+)?)",
                text,
                report
            );

            // Cholesterol
            extractAndSave(
                "Cholesterol",
                "mg/dL",
                "(?i)cholester[a-z0-9]*\\s*[:=\\-]?\\s*(\\d+(?:\\.\\d+)?)",
                text,
                report
            );


    }

    private void extractAndSave(String name, String unit,String regex, String text, MedicalReport report) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

         if (matcher.find()) {
            try {
                String valueStr = matcher.group(matcher.groupCount());

            if (valueStr == null || valueStr.trim().isEmpty()) {
                return; // skip invalid OCR match
            }

            Double value = Double.parseDouble(valueStr.trim());

                // OCR correction for Hemoglobin (decimal missing)
                if (name.equalsIgnoreCase("Hemoglobin") && value > 25) {
                    value = value / 10;
                }


            HealthParameter parameter = new HealthParameter();
            parameter.setParameterName(name);
            parameter.setParameterValue(value);
            parameter.setUnit(unit);
            parameter.setStatus("PENDING");
            parameter.setMedicalReport(report);

            parameterRepository.save(parameter);

        } catch (Exception e) {
            // Ignore malformed OCR values safely
            System.out.println("Failed to extract " + name + " due to OCR noise");
        }
    }
}

}
