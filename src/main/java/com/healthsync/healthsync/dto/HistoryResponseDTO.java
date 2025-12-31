package com.healthsync.healthsync.dto;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryResponseDTO {

    private Long reportId;
    private String fileName;
    private LocalDateTime uploadDate;
    private List<HealthParameterDTO> parameters;

    public HistoryResponseDTO(Long reportId,
                              String fileName,
                              LocalDateTime uploadDate,
                              List<HealthParameterDTO> parameters) {
        this.reportId = reportId;
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.parameters = parameters;
    }

    public Long getReportId() {
        return reportId;
    }

    public String getFileName() {
        return fileName;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public List<HealthParameterDTO> getParameters() {
        return parameters;
    }
}
