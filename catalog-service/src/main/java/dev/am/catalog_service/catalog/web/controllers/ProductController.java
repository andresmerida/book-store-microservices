package dev.am.catalog_service.catalog.web.controllers;

import dev.am.catalog_service.catalog.domain.PagedResult;
import dev.am.catalog_service.catalog.domain.ProductDTO;
import dev.am.catalog_service.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<PagedResult<ProductDTO>> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return ResponseEntity.ok(productService.findAllPageable(pageNo));
    }
}
