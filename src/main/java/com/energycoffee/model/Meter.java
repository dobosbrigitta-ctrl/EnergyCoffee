package com.energycoffee.model;

import jakarta.persistence.*;

@Entity
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String meterNumber;

    @Column(nullable = false)
    private String meterType;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}