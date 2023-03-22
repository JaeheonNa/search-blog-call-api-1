package com.kakao.searchblogcallkakaoapi.serviceFactory;

import com.kakao.searchblogcallkakaoapi.service.BlogSearchService;
import com.kakao.searchblogcallkakaoapi.serviceImpl.BlogSearchServiceImpl_kakao_001;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogSearchServiceFactory {
    private final BlogSearchServiceImpl_kakao_001 blogSearchServiceImpl_kakao_001;

    public BlogSearchService getBlogSearchService(String apiType){
        return blogSearchServiceImpl_kakao_001;
    }



}
