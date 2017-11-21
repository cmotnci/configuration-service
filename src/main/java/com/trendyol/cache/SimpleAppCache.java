package com.trendyol.cache;

import com.trendyol.model.Configuration;

import java.util.HashMap;
import java.util.List;

public class SimpleAppCache {

    private static HashMap<String, List<Configuration>> configurationMap = new HashMap<>();

    public HashMap<String, List<Configuration>> getConfigurationMap() {
        return configurationMap;
    }
}
