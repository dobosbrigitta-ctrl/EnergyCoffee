
package com.energycoffee.service;

import com.energycoffee.model.Site;
import com.energycoffee.repository.EnergyRecordRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnergyRecordServiceTest {

    @Mock
    private EnergyRecordRepository repository;

    @Mock
    private SiteService siteService;

    @InjectMocks
    private EnergyRecordService energyRecordService;
}
