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
}
