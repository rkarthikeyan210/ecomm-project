package com.ecommproject.productservice.service;

import com.ecommproject.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);

    List<Product> getAllProducts();
}