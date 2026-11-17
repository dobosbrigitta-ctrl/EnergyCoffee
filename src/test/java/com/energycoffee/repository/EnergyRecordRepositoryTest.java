package com.energycoffee.repository;

import com.energycoffee.model.EnergyRecord;
import com.energycoffee.model.Site;
import com.energycoffee.repository.EnergyRecordRepository;
import com.energycoffee.repository.SiteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
class EnergyRecordRepositoryTest {

    @Autowired
    private EnergyRecordRepository energyRecordRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Test
    void shouldSaveEnergyRecordWithSite() {

        Site site = new Site();
        site.setName("Test Office");
        site.setCity("Budapest");

        Site savedSite = siteRepository.save(site);

        EnergyRecord record = new EnergyRecord();
        record.setConsumption(850.0);
        record.setRecordedAt(LocalDateTime.now());
        record.setSite(savedSite);

        EnergyRecord savedRecord = energyRecordRepository.save(record);

        assertThat(savedRecord.getId()).isNotNull();
        assertThat(savedRecord.getSite().getId()).isEqualTo(savedSite.getId());

        EnergyRecord foundRecord = energyRecordRepository
                .findById(savedRecord.getId())
                .orElseThrow();

        assertThat(foundRecord.getSite().getName()).isEqualTo("Test Office");
    }
    @Test
    void shouldNotSaveEnergyRecordWithoutSite() {

        EnergyRecord record = new EnergyRecord();
        record.setConsumption(850.0);
        record.setRecordedAt(LocalDateTime.now());

        assertThatThrownBy(() -> {
            energyRecordRepository.saveAndFlush(record);
        }).isInstanceOf(Exception.class);
    }
}