package com.java.jeju.jeju.recommend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.java.jeju.jeju.recommend.domain.Recommend;
import com.java.jeju.jeju.recommend.domain.RecommendList;

@Service
public class RecommendService {

    @Value("${visitjeju.api.url}")
    private String apiUrl;

    public List<Recommend> getRecommends(String apiKey, String locale, int page, String cid) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apiKey", apiKey)
                .queryParam("locale", locale)
                .queryParam("page", page)
                .queryParam("cid", cid)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        RecommendList response = restTemplate.getForObject(url, RecommendList.class);

        if (response != null) {
            return response.getItems();
        }

        return Collections.emptyList();
    }
    
    public String getSeogwipoContentsId(String apiKey, String locale, int page, String cid) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apiKey", apiKey)
                .queryParam("locale", "kr")
                .queryParam("page", 1)
                .queryParam("region1cd", "서귀포시")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        RecommendList response = restTemplate.getForObject(url, RecommendList.class);

        if (response != null && response.getItems() != null && response.getItems().size() > 0) {
        
            return response.getItems().get(0).getContentsid();
        }

        return null;
    }
    
    public String getJejuContentsId(String apiKey, String locale, int page, String cid) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apiKey", apiKey)
                .queryParam("locale", "kr")
                .queryParam("page", 1)
                .queryParam("address", "제주시")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        RecommendList response = restTemplate.getForObject(url, RecommendList.class);

        if (response != null && response.getItems() != null && response.getItems().size() > 0) {
            // 여기서 첫 번째 콘텐츠의 ID를 가져옴
            return response.getItems().get(1).getContentsid();
        }

        return null;
    }
    
    
    
    
}