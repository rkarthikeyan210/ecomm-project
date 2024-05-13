package com.ecommproject.productservice.service;

import com.ecommproject.productservice.dto.FakeStoreProductDto;
import com.ecommproject.productservice.model.Product;
import com.ecommproject.productservice.util.ProductConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    public RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        return ProductConversion.convertToProduct(fakeStoreProductDto);
    }
}
