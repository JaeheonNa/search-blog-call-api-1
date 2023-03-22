package com.kakao.searchblogcallkakaoapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class KakaoResponse {
    List<Documents> documents;
    Meta meta;
}
