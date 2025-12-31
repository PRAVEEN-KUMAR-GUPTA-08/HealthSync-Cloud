package com.healthsync.healthsync.dto;

public class HealthParameterDTO {

    private String name;
    private Double value;
    private String unit;
    private String status;

    public HealthParameterDTO(String name, Double value, String unit, String status) {
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getStatus() {
        return status;
    }
}
