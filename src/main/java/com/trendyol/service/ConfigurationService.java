package com.trendyol.service;

import com.trendyol.cache.SimpleAppCache;
import com.trendyol.enums.ConfigurationStatusEnum;
import com.trendyol.model.Configuration;
import com.trendyol.persistence.repositories.ConfigurationRepository;
import com.trendyol.utils.EntityConverter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;
    private final EntityConverter entityConverter;
    private final SimpleAppCache simpleAppCache;

    private static final String CONFIGURATION_LIST = "configurationList";

    public List<Configuration> getActiveConfigurationsByApplicationName(String applicationName) {
        List<Configuration> configurationList = new ArrayList<>();
        HashMap<String, List<Configuration>> configurationMap = simpleAppCache.getConfigurationMap();

        if (configurationMap.containsKey(CONFIGURATION_LIST)) {
            for (Configuration configuration : configurationMap.get(CONFIGURATION_LIST)) {
                if (configuration.getApplicationName().equalsIgnoreCase(applicationName)
                        && configuration.getStatus() == ConfigurationStatusEnum.ACTIVE.getStatus()) {
                    configurationList.add(configuration);
                }
            }
        }

        if (!configurationList.isEmpty()) {
            return configurationList;
        }

        configurationList = entityConverter.convert(configurationRepository.findByApplicationNameAndStatus(applicationName,
                ConfigurationStatusEnum.ACTIVE.getStatus()));

        if (configurationList.isEmpty()) {
            if (configurationMap.containsKey(applicationName)) {
                return configurationMap.get(applicationName);
            }

            return configurationList;
        }

        configurationMap.put(applicationName, configurationList);
        return configurationList;
    }

    public void addConfiguration(String name, String type, String value, int status, String applicationName) {
        configurationRepository.save(
                entityConverter.convert(
                        Configuration.builder()
                                .name(name)
                                .type(type)
                                .value(value)
                                .status(status)
                                .applicationName(applicationName)
                                .status(ConfigurationStatusEnum.ACTIVE.getStatus())
                                .build()
                )
        );
    }

    public void updateConfiguration(Long id, String name, String type, String value, int status, String applicationName) {
        Configuration configuration = entityConverter.convert(configurationRepository.findById(id));

        if (!configuration.getName().isEmpty()) {
            configuration.setName(name);
            configuration.setType(type);
            configuration.setValue(value);
            configuration.setStatus(status);
            configuration.setApplicationName(applicationName);
            configurationRepository.save(entityConverter.convert(configuration));
        }
    }

    public void updateConfigurationMap() {
        List<Configuration> configurationList = entityConverter.convert(configurationRepository.findAllByStatus(ConfigurationStatusEnum.ACTIVE.getStatus()));
        simpleAppCache.getConfigurationMap().put(CONFIGURATION_LIST, configurationList);
    }
}
