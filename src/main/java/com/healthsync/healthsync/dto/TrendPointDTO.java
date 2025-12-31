package com.healthsync.healthsync.dto;

import java.time.LocalDateTime;

public class TrendPointDTO {

    private LocalDateTime date;
    private Double value;
    private String status;

    public TrendPointDTO(LocalDateTime date, Double value, String status) {
        this.date = date;
        this.value = value;
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }
}
