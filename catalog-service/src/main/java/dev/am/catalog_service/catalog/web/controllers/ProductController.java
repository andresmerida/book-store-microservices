package dev.am.catalog_service.catalog.web.controllers;

import dev.am.catalog_service.catalog.domain.PagedResult;
import dev.am.catalog_service.catalog.domain.ProductDTO;
import dev.am.catalog_service.catalog.domain.ProductService;
import dev.am.catalog_service.catalog.web.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{code}")
    ResponseEntity<ProductDTO> getProductByCode(@PathVariable String code) {
        return productService
                .findByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
