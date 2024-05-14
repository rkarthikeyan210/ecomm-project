package com.ecommproject.productservice.controller;

import com.ecommproject.productservice.model.Product;
import com.ecommproject.productservice.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    public ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId)
    {
        return productService.getProduct(productId);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Custom-Value");
        return new ResponseEntity<>(productService.getAllProducts(), headers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product createdProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
