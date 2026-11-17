package com.energycoffee.repository;

import com.energycoffee.model.EnergyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyRecordRepository extends JpaRepository<EnergyRecord, Long> {
}