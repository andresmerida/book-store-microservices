package dev.am.catalog_service.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product code is required") private String code;

    @Column(nullable = false)
    @NotBlank(message = "Product name is required") private String name;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    @DecimalMin("0.1") @NotNull(message = "Product price is required") private BigDecimal price;
}
