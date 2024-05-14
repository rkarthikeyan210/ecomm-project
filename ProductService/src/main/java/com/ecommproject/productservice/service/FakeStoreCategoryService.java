package com.ecommproject.productservice.service;

import com.ecommproject.productservice.client.FakeStoreClient;
import com.ecommproject.productservice.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FakeStoreCategoryService implements CategoryService {
    private final FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreCategoryService(@Qualifier("fakeStoreWebClient") FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] categories = fakeStoreClient.getAllCategories();

        List<Category> categoryList = new ArrayList<>();
        AtomicLong counter = new AtomicLong();
        for(String category: categories) {
            categoryList.add(new Category(counter.incrementAndGet(), category));
        }
        return categoryList;
    }

    @Override
    public Category createCategory(Category category) {
        Category categoryCreated =  new Category();
        categoryCreated.setId(category.getId());
        categoryCreated.setName(category.getName());

        return categoryCreated;
    }
}
