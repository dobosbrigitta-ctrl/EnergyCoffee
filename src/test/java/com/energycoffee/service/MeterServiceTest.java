package com.energycoffee.service;

import com.energycoffee.exception.ResourceNotFoundException;
import com.energycoffee.model.Meter;
import com.energycoffee.model.Site;
import com.energycoffee.repository.MeterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeterServiceTest {

    @Mock
    private MeterRepository meterRepository;

    @InjectMocks
    private MeterService meterService;

    private Site site;
    private Meter meter;

    @BeforeEach
    void setUp() {
        site = new Site();
        site.setExternalId("SITE-001");
        site.setName("Test Site");
        site.setCity("Budapest");

        meter = new Meter();
        meter.setMeterNumber("METER-001");
        meter.setMeterType("Electricity");
        meter.setSite(site);
    }

    @Test
    void shouldSaveMeter() {
        when(meterRepository.save(meter)).thenReturn(meter);

        Meter result = meterService.saveMeter(meter);

        assertSame(meter, result);
        verify(meterRepository).save(meter);
    }

    @Test
    void shouldReturnAllMeters() {
        when(meterRepository.findAll()).thenReturn(List.of(meter));

        List<Meter> result = meterService.getAllMeters();

        assertEquals(1, result.size());
        assertSame(meter, result.get(0));
        verify(meterRepository).findAll();
    }

    @Test
    void shouldReturnMeterById() {
        when(meterRepository.findById(1L)).thenReturn(Optional.of(meter));

        Meter result = meterService.getMeterById(1L);

        assertSame(meter, result);
        verify(meterRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenMeterNotFound() {
        when(meterRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> meterService.getMeterById(1L)
        );

        verify(meterRepository).findById(1L);
    }

    @Test
    void shouldUpdateMeter() {
        Meter updatedMeter = new Meter();
        updatedMeter.setMeterNumber("METER-002");
        updatedMeter.setMeterType("Gas");
        updatedMeter.setSite(site);

        when(meterRepository.findById(1L)).thenReturn(Optional.of(meter));
        when(meterRepository.save(meter)).thenReturn(meter);

        Meter result = meterService.updateMeter(1L, updatedMeter);

        assertEquals("METER-002", result.getMeterNumber());
        assertEquals("Gas", result.getMeterType());
        assertSame(site, result.getSite());

        verify(meterRepository).save(meter);
    }

    @Test
    void shouldDeleteMeter() {
        when(meterRepository.findById(1L)).thenReturn(Optional.of(meter));

        meterService.deleteMeter(1L);

        verify(meterRepository).delete(meter);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingMeter() {
        when(meterRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> meterService.deleteMeter(1L)
        );

        verify(meterRepository, never()).delete(any(Meter.class));
    }
}