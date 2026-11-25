package com.energycoffee.repository;

import com.energycoffee.model.Meter;
import com.energycoffee.model.Site;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MeterRepositoryTest {

    @Autowired
    private MeterRepository meterRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Test
    void shouldSaveAndFindMeter() {
        Site site = createSite();
        siteRepository.save(site);

        Meter meter = createMeter(site);
        Meter savedMeter = meterRepository.save(meter);

        assertNotNull(savedMeter.getId());

        Meter foundMeter = meterRepository.findById(savedMeter.getId())
                .orElseThrow();

        assertEquals("METER-001", foundMeter.getMeterNumber());
        assertEquals("Electricity", foundMeter.getMeterType());
        assertEquals(site.getId(), foundMeter.getSite().getId());
    }

    @Test
    void shouldFindAllMeters() {
        Site site = createSite();
        siteRepository.save(site);

        meterRepository.save(createMeter(site));
        meterRepository.save(createSecondMeter(site));

        assertEquals(2, meterRepository.findAll().size());
    }

    @Test
    void shouldDeleteMeter() {
        Site site = createSite();
        siteRepository.save(site);

        Meter meter = meterRepository.save(createMeter(site));

        meterRepository.delete(meter);

        assertFalse(meterRepository.findById(meter.getId()).isPresent());
    }

    private Site createSite() {
        Site site = new Site();
        site.setExternalId("SITE-001");
        site.setName("Test Site");
        site.setCity("Budapest");
        site.setAddress("Test Street 1");
        return site;
    }

    private Meter createMeter(Site site) {
        Meter meter = new Meter();
        meter.setMeterNumber("METER-001");
        meter.setMeterType("Electricity");
        meter.setSite(site);
        return meter;
    }

    private Meter createSecondMeter(Site site) {
        Meter meter = new Meter();
        meter.setMeterNumber("METER-002");
        meter.setMeterType("Gas");
        meter.setSite(site);
        return meter;
    }
}