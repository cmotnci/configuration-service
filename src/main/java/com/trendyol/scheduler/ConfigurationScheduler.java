package com.trendyol.scheduler;

import com.trendyol.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationScheduler {

    @Autowired
    private ConfigurationService configurationService;

    @Scheduled(fixedDelay = 60000)
    public void configurationScheduler() {
        configurationService.updateConfigurationMap();
    }
}
