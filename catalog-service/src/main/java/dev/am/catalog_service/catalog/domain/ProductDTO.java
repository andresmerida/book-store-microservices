package dev.am.catalog_service.catalog.domain;

import java.math.BigDecimal;

public record ProductDTO(Long id, String code, String name, String description, String imageUrl, BigDecimal price) {}
