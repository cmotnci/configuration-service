package com.trendyol.utils;

import com.trendyol.model.Configuration;
import com.trendyol.persistence.entities.ConfigurationEntity;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class EntityConverter {

    private final DozerBeanMapper dozerBeanMapper;

    public ConfigurationEntity convert(Configuration configuration) {
        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        dozerBeanMapper.map(configuration, configurationEntity);

        if (configurationEntity.getId().compareTo(0L) == 0) {
            configurationEntity.setId(null);
        }

        return configurationEntity;
    }

    public Configuration convert(ConfigurationEntity configurationEntity) {
        Configuration configuration = new Configuration();
        dozerBeanMapper.map(configurationEntity, configuration);

        return configuration;
    }

    public List<Configuration> convert(List<ConfigurationEntity> configurationEntityList) {
        List<Configuration> configurationList = new ArrayList<>();

        for (ConfigurationEntity configurationEntity : configurationEntityList) {
            Configuration configuration = new Configuration();
            dozerBeanMapper.map(configurationEntity, configuration);
            configurationList.add(configuration);
        }

        return configurationList;
    }
}
