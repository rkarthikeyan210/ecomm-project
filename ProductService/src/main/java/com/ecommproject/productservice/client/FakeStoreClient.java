package com.ecommproject.productservice.client;

import com.ecommproject.productservice.dto.FakeStoreProductDto;

public interface FakeStoreClient {

    String fakeStoreBaseUrl = "https://fakestoreapi.com";
    FakeStoreProductDto getProduct(Long id);

    FakeStoreProductDto[] getAllProducts();

    FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto);

    FakeStoreProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto);

    FakeStoreProductDto deleteProduct(Long id);

    String[] getAllCategories();
}
