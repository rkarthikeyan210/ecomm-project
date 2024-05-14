package com.ecommproject.productservice.service;

import com.ecommproject.productservice.client.FakeStoreClient;
import com.ecommproject.productservice.dto.FakeStoreProductDto;
import com.ecommproject.productservice.model.Product;
import com.ecommproject.productservice.util.ProductConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    public FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreProductService(@Qualifier("fakeStoreWebClient") FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProduct(Long id) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.getProduct(id);

        return ProductConversion.convertToProduct(fakeStoreProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProducts = fakeStoreClient.getAllProducts();

        ArrayList<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProduct: fakeStoreProducts) {
            products.add(ProductConversion.convertToProduct(fakeStoreProduct));
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.createProduct(ProductConversion.convertToFakeStoreProduct(product));
        return ProductConversion.convertToProduct(fakeStoreProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.updateProduct(id, ProductConversion.convertToFakeStoreProduct(product));
        return ProductConversion.convertToProduct(fakeStoreProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.deleteProduct(id);
        return;
    }
}
