package dev.am.catalog_service.catalog.domain;

import dev.am.catalog_service.config.AppProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    final ProductRepository productRepository;
    final AppProperties appProperties;

    public ProductService(ProductRepository productRepository, AppProperties appProperties) {
        this.productRepository = productRepository;
        this.appProperties = appProperties;
    }

    public PagedResult<ProductDTO> findAllPageable(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1; // page init on cero
        Pageable pageable = PageRequest.of(pageNo, appProperties.pageSize(), sort);
        Page<ProductDTO> productsPage = productRepository.findAll(pageable).map(ProductMapper::toDTO);

        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }
}
