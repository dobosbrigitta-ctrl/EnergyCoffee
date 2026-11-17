package com.energycoffee;

import com.energycoffee.model.Site;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.energycoffee.model.EnergyRecord;
import com.energycoffee.repository.EnergyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import org.springframework.test.context.ActiveProfiles;
import com.energycoffee.repository.SiteRepository;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:energycoffee_test",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password="
})
@ActiveProfiles("test")
class EnergyCoffeeApplicationTests {

	@Autowired
	private EnergyRecordRepository repository;

	@Autowired
	private SiteRepository siteRepository;

	@Test
	void contextLoads() {
	}


	@Test
	void shouldSaveEnergyRecord() {
		Site site = new Site();
		site.setName("Test Office");
		site.setCity("Budapest");

		Site savedSite = siteRepository.save(site);

		EnergyRecord record = new EnergyRecord();
		record.setConsumption(850.0);
		record.setRecordedAt(LocalDateTime.now());
		record.setSite(savedSite);

		EnergyRecord savedRecord = repository.save(record);

		assertThat(savedRecord.getId()).isNotNull();
		assertThat(savedRecord.getSite().getId()).isNotNull();
	}


}
