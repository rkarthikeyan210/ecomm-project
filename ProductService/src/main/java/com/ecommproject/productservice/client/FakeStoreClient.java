package com.ecommproject.productservice.client;

import com.ecommproject.productservice.dto.FakeStoreProductDto;

public interface FakeStoreClient {

    String fakeStoreBaseUrl = "https://fakestoreapi.com";
    FakeStoreProductDto getProduct(Long id);

    FakeStoreProductDto[] getAllProducts();
}
