package com.energycoffee.dto;

public class EnergyRecordRequest {

    private Double consumption;
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