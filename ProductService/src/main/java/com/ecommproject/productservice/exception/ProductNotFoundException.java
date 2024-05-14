package com.ecommproject.productservice.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
