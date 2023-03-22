package com.kakao.searchblogcallkakaoapi.serviceImpl;

import com.kakao.searchblogcallkakaoapi.dto.KakaoResponse;
import com.kakao.searchblogcallkakaoapi.service.BlogSearchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchServiceImpl_kakao_001 implements BlogSearchService {

    @Value("${kakao.uri}")
    private String uri;
    @Value("${kakao.token}")
    private String authorization;

    private WebClient webClient;

    @PostConstruct
    public void initWebClient() {
        webClient = WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build()
                .mutate()
                .build();
    }

    @Override
    public Mono<KakaoResponse> getBlogsFromApi(String query, String sort, int page, int size){
        return webClient.get().uri(uriBuilder1 ->
                        uriBuilder1.path(this.uri)
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
