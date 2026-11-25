package com.energycoffee.controller;

import com.energycoffee.model.Meter;
import com.energycoffee.service.MeterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    private final MeterService meterService;

    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    @PostMapping
    public Meter createMeter(@Valid @RequestBody Meter meter) {
        return meterService.saveMeter(meter);
    }

    @GetMapping
    public List<Meter> getAllMeters() {
        return meterService.getAllMeters();
    }

    @GetMapping("/{id}")
    public Meter getMeterById(@PathVariable Long id) {
        return meterService.getMeterById(id);
    }

    @PutMapping("/{id}")
    public Meter updateMeter(@PathVariable Long id, @Valid @RequestBody Meter meter) {
        return meterService.updateMeter(id, meter);
    }

    @DeleteMapping("/{id}")
    public void deleteMeter(@PathVariable Long id) {
        meterService.deleteMeter(id);
    }
}