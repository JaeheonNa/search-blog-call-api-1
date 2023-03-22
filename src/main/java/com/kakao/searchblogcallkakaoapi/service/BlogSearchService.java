package com.kakao.searchblogcallkakaoapi.service;

import com.kakao.searchblogcallkakaoapi.dto.KakaoResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface BlogSearchService {
    Mono<KakaoResponse> getBlogsFromApi(String query, String sort, int page, int size);
}
