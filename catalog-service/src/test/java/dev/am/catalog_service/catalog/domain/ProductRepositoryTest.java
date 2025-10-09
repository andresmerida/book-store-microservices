package dev.am.catalog_service.catalog.domain;

import static dev.am.catalog_service.util.ConstantsTestUtil.SPRING_TEST_DATABASE_REPLACE_NONE_VALUE_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

import dev.am.catalog_service.containers.PostgreSQLContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            SPRING_TEST_DATABASE_REPLACE_NONE_VALUE_PROPERTY, // Avoid to load default h2 database in memory
        })
@Import(PostgreSQLContainerConfig.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        assertThat(productRepository.findAll()).hasSize(15);
    }
}
