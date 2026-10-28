package com.energycoffee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.energycoffee.model.EnergyRecord;
import com.energycoffee.repository.EnergyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EnergyCoffeeApplicationTests {

	@Autowired
	private EnergyRecordRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldSaveEnergyRecord() {
		EnergyRecord record = new EnergyRecord();
		record.setConsumption(850.0);
		record.setRecordedAt(LocalDateTime.now());

		EnergyRecord savedRecord = repository.save(record);

		assertThat(savedRecord.getId()).isNotNull();
	}

}
