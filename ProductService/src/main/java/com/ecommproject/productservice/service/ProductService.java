package com.ecommproject.productservice.service;

import com.ecommproject.productservice.exception.ProductNotFoundException;
import com.ecommproject.productservice.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
