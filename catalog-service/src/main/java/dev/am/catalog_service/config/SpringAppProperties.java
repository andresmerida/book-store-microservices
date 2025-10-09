package dev.am.catalog_service.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "spring.application")
public record SpringAppProperties(@NotBlank String name, @DefaultValue("10") @Min(1) int pageSize) {}
