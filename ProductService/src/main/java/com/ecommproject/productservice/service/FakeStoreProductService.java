package com.ecommproject.productservice.service;

import com.ecommproject.productservice.client.FakeStoreClient;
import com.ecommproject.productservice.dto.FakeStoreProductDto;
import com.ecommproject.productservice.exception.ProductNotFoundException;
import com.ecommproject.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    public FakeStoreClient fakeStoreClient;
    public RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductService(@Qualifier("fakeStoreWebClient") FakeStoreClient fakeStoreClient,
                                   RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
        //check the cache first
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
        if (product != null) {
            return product;
        }

        //get the product from the FakeStore API
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.getProduct(id);

        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException(String.format("Product with id %s is not exist.", id));
        }

        //store the product in the cache
        product = Product.from(fakeStoreProduct);
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, product);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProducts = fakeStoreClient.getAllProducts();

        ArrayList<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProduct: fakeStoreProducts) {
            products.add(Product.from(fakeStoreProduct));
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.createProduct(FakeStoreProductDto.from(product));
        return Product.from(fakeStoreProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.updateProduct(id, FakeStoreProductDto.from(product));
        return Product.from(fakeStoreProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        FakeStoreProductDto fakeStoreProduct = fakeStoreClient.deleteProduct(id);
        return;
    }
}
