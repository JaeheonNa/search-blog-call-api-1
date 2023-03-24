package com.kakao.searchblogcallkakaoapi.serviceImpl;

import com.kakao.searchblogcallkakaoapi.dto.KakaoResponse;
import com.kakao.searchblogcallkakaoapi.service.BlogSearchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchServiceImpl_kakao_001 implements BlogSearchService {
    @Value("${kakao.token}")
    private String authorization;

    @Qualifier("kakaoWebClient")
    private final WebClient webClient;

    @Override
    public Mono<KakaoResponse> getBlogsFromApi(String query, String sort, int page, int size){
        return webClient.get().uri(uriBuilder ->
                        uriBuilder.path("/v2/search/blog")
                                .queryParam("query", query)
                                .queryParam("sort", sort)
                                .queryParam("page", page)
                                .queryParam("size", size)
                                .build()
                ).headers(headers -> {
                    headers.add("Authorization", this.authorization);
                }).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KakaoResponse.class);
    }

}
