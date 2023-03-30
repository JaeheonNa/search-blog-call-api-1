package com.kakao.searchblogcallkakaoapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${kakao.baseUri}")
    String kakaoBaseUri;
    @Bean(name="kakaoWebClient")
    public WebClient kakaoWebClient(){
        return WebClient.builder()
                .baseUrl(this.kakaoBaseUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build()
                .mutate()
                .build();
    }
}
