package dev.am.catalog_service.catalog.domain;

final class ProductMapper {
    public static ProductDTO toDTO(ProductEntity entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getPrice());
    }
}
