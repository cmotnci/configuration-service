package com.trendyol.service;

import com.trendyol.cache.SimpleAppCache;
import com.trendyol.enums.ConfigurationStatusEnum;
import com.trendyol.model.Configuration;
import com.trendyol.persistence.repositories.ConfigurationRepository;
import com.trendyol.utils.EntityConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ConfigurationServiceTest {

    @Mock
    private EntityConverter entityConverter;

    @Mock
    private ConfigurationRepository configurationRepository;

    @Mock
    private SimpleAppCache simpleAppCache;

    private ConfigurationService configurationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        configurationService = new ConfigurationService(configurationRepository, entityConverter, simpleAppCache);
    }

    @Test
    public void shouldHaveNoInteractionWithEntityConverter() throws Exception {
        HashMap<String, List<Configuration>> configurationMap = new HashMap<>();
        List<Configuration> configurationList = new ArrayList<>();
        configurationList.add(
                Configuration.builder()
                        .id(1L)
                        .name("Test")
                        .value("Test value")
                        .type("String")
                        .status(ConfigurationStatusEnum.ACTIVE.getStatus())
                        .applicationName("Test app")
                        .build()
        );

        configurationMap.put("configurationList", configurationList);

        when(simpleAppCache.getConfigurationMap()).thenReturn(configurationMap);

        configurationService.getActiveConfigurationsByApplicationName("Test app");

        verifyZeroInteractions(entityConverter);
    }
}
