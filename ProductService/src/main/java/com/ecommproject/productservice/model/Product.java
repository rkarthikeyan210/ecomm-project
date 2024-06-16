package com.ecommproject.productservice.model;

import com.ecommproject.productservice.dto.FakeStoreProductDto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Product implements Serializable {
    private Long id;
    private String title;
    private Double price;
    private Category category;
    private String description;
    private String imageUrl;

    public static Product from(FakeStoreProductDto fakeStoreProductDto) {
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
}
