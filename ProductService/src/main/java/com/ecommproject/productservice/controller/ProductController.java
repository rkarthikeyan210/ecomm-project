package com.ecommproject.productservice.controller;

import com.ecommproject.productservice.model.Product;
import com.ecommproject.productservice.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
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
}
