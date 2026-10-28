package com.energycoffee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EnergyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double consumption;
    private java.time.LocalDateTime recordedAt;

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public void setRecordedAt(java.time.LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }
}