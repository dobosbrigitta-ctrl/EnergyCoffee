package com.energycoffee.controller;

import com.energycoffee.model.EnergyRecord;
import com.energycoffee.service.EnergyRecordService;
import org.springframework.web.bind.annotation.*;
import com.energycoffee.dto.EnergyRecordRequest;

import java.util.List;

@RestController
@RequestMapping("/api/energy-records")
public class EnergyRecordController {

    private final EnergyRecordService energyRecordService;

    public EnergyRecordController(EnergyRecordService energyRecordService) {
        this.energyRecordService = energyRecordService;
    }

    @GetMapping
    public List<EnergyRecord> getAllRecords() {
        return energyRecordService.getAllRecords();
    }

    @PostMapping
    public EnergyRecord createRecord(@RequestBody EnergyRecordRequest request) {
        return energyRecordService.saveConsumption(
                request.getConsumption(),
                request.getSiteId()
        );
    }
}