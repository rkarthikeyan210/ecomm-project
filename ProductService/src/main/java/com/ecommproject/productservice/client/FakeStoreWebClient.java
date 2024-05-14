package com.ecommproject.productservice.client;

import com.ecommproject.productservice.dto.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FakeStoreWebClient implements FakeStoreClient {

    private final WebClient webClient;

    @Autowired
    public FakeStoreWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public FakeStoreProductDto getProduct(Long id) {
        String url = fakeStoreBaseUrl + "/products/" + id;
        Mono<FakeStoreProductDto> response = webClient.get().uri(url).retrieve().bodyToMono(FakeStoreProductDto.class);
        return response.block();
    }

    @Override
    public FakeStoreProductDto[] getAllProducts() {
        String url = fakeStoreBaseUrl + "/products";
        Mono<FakeStoreProductDto[]> response = webClient.get().uri(url).retrieve().bodyToMono(FakeStoreProductDto[].class);

        return response.block();
    }

    @Override
    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) {
        String url = fakeStoreBaseUrl + "/products";
        Mono<FakeStoreProductDto> response = webClient.post()
                .uri(url)
                .body(Mono.just(fakeStoreProductDto), FakeStoreProductDto.class)
                .retrieve().bodyToMono(FakeStoreProductDto.class);
        return response.block();
    }

    @Override
    public FakeStoreProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        String url = fakeStoreBaseUrl + "/products/" + id;
        Mono<FakeStoreProductDto> response = webClient.put()
                .uri(url)
                .body(Mono.just(fakeStoreProductDto), FakeStoreProductDto.class)
                .retrieve().bodyToMono(FakeStoreProductDto.class);
        return response.block();
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        String url = fakeStoreBaseUrl + "/products/" + id;
        Mono<FakeStoreProductDto> response = webClient.delete()
                .uri(url)
                .retrieve().bodyToMono(FakeStoreProductDto.class);
        return response.block();
    }

    @Override
    public String[] getAllCategories() {
        String url = fakeStoreBaseUrl + "/products/categories";
        Mono<String[]> response = webClient.get().uri(url).retrieve().bodyToMono(String[].class);
        return response.block();
    }
}
