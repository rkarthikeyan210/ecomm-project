package com.ecommproject.productservice.client;

import com.ecommproject.productservice.dto.FakeStoreProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreRestTemplateClient implements FakeStoreClient {

    private final RestTemplate restTemplate;

    public FakeStoreRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public FakeStoreProductDto getProduct(Long id) {
        String url = fakeStoreBaseUrl + "/products/" + id;
        return restTemplate.getForObject(url, FakeStoreProductDto.class);
    }

    @Override
    public FakeStoreProductDto[] getAllProducts() {
        String url = fakeStoreBaseUrl + "/products";
        return restTemplate.getForObject(url, FakeStoreProductDto[].class);
    }

    @Override
    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public FakeStoreProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public String[] getAllCategories() {
        return new String[0];
    }
}
