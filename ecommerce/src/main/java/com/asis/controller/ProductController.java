package com.asis.controller;

import com.asis.entity.Product;
import com.asis.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products") // The API endpoint for Treasures
public class ProductController {

    private final ProductService productService;

    // Dependency Injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products -> List all Treasures
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // GET /api/products/1 -> Get a single Treasure
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Assuming your ProductService.get method accepts Long ID now
        Product product = productService.get(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // POST /api/products -> Create a new Treasure (Admin/Captain function)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.create(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Call the service method to handle the deletion logic
        productService.delete(id);
        // Returns 204 No Content, which is standard for a successful DELETE
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}