package com.trendyol.configuration;

import com.trendyol.cache.SimpleAppCache;
import com.trendyol.persistence.repositories.ConfigurationRepository;
import com.trendyol.service.ConfigurationService;
import com.trendyol.utils.EntityConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ConfigurationService configurationService(ConfigurationRepository configurationRepository,
                                                     EntityConverter entityConverter,
                                                     SimpleAppCache simpleAppCache) {
        return new ConfigurationService(configurationRepository, entityConverter, simpleAppCache);
    }

    @Bean
    public SimpleAppCache simpleAppCache() {
        return new SimpleAppCache();
    }

    @Bean EntityConverter entityConverter(DozerBeanMapper dozerBeanMapper) {
        return new EntityConverter(dozerBeanMapper);
    }

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper();
    }
}
