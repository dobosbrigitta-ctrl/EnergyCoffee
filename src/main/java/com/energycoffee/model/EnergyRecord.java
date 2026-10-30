
package com.energycoffee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


import java.time.LocalDateTime;

@Entity
public class EnergyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double consumption;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;


    public EnergyRecord() {
    }



    public Long getId() {
        return id;
    }

    public Double getConsumption() {
        return consumption;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }



    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
