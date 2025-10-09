package dev.am.catalog_service;

import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@Import(PostgreSQLContainer.class)
class TestcontainersConfiguration {}
