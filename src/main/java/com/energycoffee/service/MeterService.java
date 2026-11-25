package com.energycoffee.service;

import com.energycoffee.exception.ResourceNotFoundException;
import com.energycoffee.model.Meter;
import com.energycoffee.repository.MeterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {

    private final MeterRepository meterRepository;

    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public Meter saveMeter(Meter meter) {
        return meterRepository.save(meter);
    }

    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    public Meter getMeterById(Long id) {
        return meterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Meter not found with ID: " + id));
    }

    public Meter updateMeter(Long id, Meter updatedMeter) {
        Meter existingMeter = getMeterById(id);

        existingMeter.setMeterNumber(updatedMeter.getMeterNumber());
        existingMeter.setMeterType(updatedMeter.getMeterType());
        existingMeter.setSite(updatedMeter.getSite());

        return meterRepository.save(existingMeter);
    }

    public void deleteMeter(Long id) {
        Meter meter = getMeterById(id);
        meterRepository.delete(meter);
    }
}