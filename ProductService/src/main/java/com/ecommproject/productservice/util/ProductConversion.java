package com.ecommproject.productservice.util;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.ecommproject.productservice.dto.FakeStoreProductDto;
import com.ecommproject.productservice.model.Category;
import com.ecommproject.productservice.model.Product;

public class ProductConversion {
    public static Product convertToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();

        if (fakeStoreProductDto == null) {
            return product;
        }

        if (fakeStoreProductDto.getId() != null) {
            product.setId(fakeStoreProductDto.getId());
        }
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    public static FakeStoreProductDto convertToFakeStoreProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        if (product == null) {
            return fakeStoreProductDto;
        }

        if (product.getId() != null) {
            fakeStoreProductDto.setId(product.getId());
        }
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        return fakeStoreProductDto;
    }
}
