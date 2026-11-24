package com.energycoffee.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EnergyRecordRequest {

    @NotNull
    @Positive
    private Double consumption;

    @NotNull
    @Positive
    private Long siteId;

    public EnergyRecordRequest() {
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}