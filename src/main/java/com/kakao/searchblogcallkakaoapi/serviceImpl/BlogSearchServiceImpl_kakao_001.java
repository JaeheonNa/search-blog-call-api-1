package com.kakao.searchblogcallkakaoapi.serviceImpl;

import com.kakao.searchblogcallkakaoapi.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchServiceImpl_kakao_001 implements BlogSearchService {

    private String reqUri = "https://dapi.kakao.com/v2/search/blog";
    private String authorization = "KakaoAK 7905d8d8bf472226bd6f6c0900b0b4b5";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Map getBlogsFromApi(String query, String sort, int page, int size){
        Map response   = null;

        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(this.reqUri)
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("size", size)
                .queryParam("page", page)
                .encode(StandardCharsets.UTF_8)
                .build(false);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.authorization);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(headers);

        response = restTemplate.exchange(uriBuilder.toUri(), HttpMethod.GET, entity, Map.class).getBody();
        return response;
    }

}
