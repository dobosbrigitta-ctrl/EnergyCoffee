
package com.energycoffee.service;

import com.energycoffee.model.EnergyRecord;
import com.energycoffee.repository.EnergyRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnergyRecordService {

    private final EnergyRecordRepository repository;

    public EnergyRecordService(EnergyRecordRepository repository) {
        this.repository = repository;
    }

    public EnergyRecord saveConsumption(Double consumption) {
        EnergyRecord record = new EnergyRecord();
        record.setConsumption(consumption);
        record.setRecordedAt(LocalDateTime.now());

        return repository.save(record);
    }
}
