package com.healthsync.healthsync.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "health_parameters")
public class HealthParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String parameterName;

    @Column(nullable = false)
    private Double parameterValue;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private String status; // NORMAL or ABNORMAL

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private MedicalReport medicalReport;

    // Constructors
    public HealthParameter() {
    }

    public HealthParameter(String parameterName, Double parameterValue,
            String unit, String status, MedicalReport medicalReport) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.unit = unit;
        this.status = status;
        this.medicalReport = medicalReport;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Double parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MedicalReport getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(MedicalReport medicalReport) {
        this.medicalReport = medicalReport;
    }
}
