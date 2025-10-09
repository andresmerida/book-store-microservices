package dev.am.catalog_service.containers;

import static dev.am.catalog_service.util.ConstantsTestUtil.POSTGRES_DOCKER_IMAGE;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class PostgreSQLContainerConfig {
    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_DOCKER_IMAGE));
    }
}
