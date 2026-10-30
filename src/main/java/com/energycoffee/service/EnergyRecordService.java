package com.energycoffee.service;

import com.energycoffee.model.EnergyRecord;
import com.energycoffee.model.Site;
import com.energycoffee.repository.EnergyRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class EnergyRecordService {

    private final EnergyRecordRepository repository;
    private final SiteService siteService;

    public EnergyRecordService(
            EnergyRecordRepository repository,
            SiteService siteService) {
        this.repository = repository;
        this.siteService = siteService;
    }

    public EnergyRecord saveConsumption(Double consumption, Long siteId) {
        Site site = siteService.getSiteById(siteId);

        EnergyRecord record = new EnergyRecord();
        record.setConsumption(consumption);
        record.setRecordedAt(LocalDateTime.now());
        record.setSite(site);



        return repository.save(record);
    }

    public List<EnergyRecord> getAllRecords() {
        return repository.findAll();
    }
}