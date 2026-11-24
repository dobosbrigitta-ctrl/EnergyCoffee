package com.energycoffee.controller;

import com.energycoffee.model.EnergyRecord;
import com.energycoffee.service.EnergyRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.energycoffee.dto.EnergyRecordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping
    public EnergyRecord createRecord(@Valid @RequestBody EnergyRecordRequest request) {
        return energyRecordService.saveConsumption(
                request.getConsumption(),
                request.getSiteId()
        );
    }
}