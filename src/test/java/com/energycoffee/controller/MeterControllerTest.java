package com.energycoffee.controller;

import com.energycoffee.exception.ResourceNotFoundException;
import com.energycoffee.model.Meter;
import com.energycoffee.service.MeterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MeterController.class)
class MeterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MeterService meterService;

    @Test
    void shouldCreateMeter() throws Exception {
        Meter meter = createMeter();

        when(meterService.saveMeter(any(Meter.class))).thenReturn(meter);

        mockMvc.perform(post("/api/meters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.meterNumber").value("METER-001"))
                .andExpect(jsonPath("$.meterType").value("Electricity"));

        verify(meterService).saveMeter(any(Meter.class));
    }

    @Test
    void shouldReturnAllMeters() throws Exception {
        when(meterService.getAllMeters()).thenReturn(List.of(createMeter()));

        mockMvc.perform(get("/api/meters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].meterNumber").value("METER-001"));

        verify(meterService).getAllMeters();
    }

    @Test
    void shouldReturnMeterById() throws Exception {
        when(meterService.getMeterById(1L)).thenReturn(createMeter());

        mockMvc.perform(get("/api/meters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.meterNumber").value("METER-001"));

        verify(meterService).getMeterById(1L);
    }

    @Test
    void shouldReturnNotFoundWhenMeterDoesNotExist() throws Exception {
        when(meterService.getMeterById(1L))
                .thenThrow(new ResourceNotFoundException("Meter not found with ID: 1"));

        mockMvc.perform(get("/api/meters/1"))
                .andExpect(status().isNotFound());

        verify(meterService).getMeterById(1L);
    }

    @Test
    void shouldUpdateMeter() throws Exception {
        Meter meter = createMeter();
        meter.setMeterNumber("METER-002");
        meter.setMeterType("Gas");

        when(meterService.updateMeter(eq(1L), any(Meter.class))).thenReturn(meter);

        mockMvc.perform(put("/api/meters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.meterNumber").value("METER-002"))
                .andExpect(jsonPath("$.meterType").value("Gas"));

        verify(meterService).updateMeter(eq(1L), any(Meter.class));
    }

    @Test
    void shouldDeleteMeter() throws Exception {
        doNothing().when(meterService).deleteMeter(1L);

        mockMvc.perform(delete("/api/meters/1"))
                .andExpect(status().isOk());

        verify(meterService).deleteMeter(1L);
    }

    private Meter createMeter() {
        Meter meter = new Meter();
        meter.setMeterNumber("METER-001");
        meter.setMeterType("Electricity");
        return meter;
    }
}