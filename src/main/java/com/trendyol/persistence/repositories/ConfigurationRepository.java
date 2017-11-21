package com.trendyol.persistence.repositories;

import com.trendyol.persistence.entities.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
    List<ConfigurationEntity> findAllByStatus(int status);
    List<ConfigurationEntity> findByApplicationNameAndStatus(String applicationName, int status);
    ConfigurationEntity findById(Long id);
}
