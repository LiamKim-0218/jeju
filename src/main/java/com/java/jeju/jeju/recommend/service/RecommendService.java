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
            List<Recommend> recommends = response.getItems();
            for (Recommend recommend : recommends) {
                // 각 Recommend 객체에 thumbnailPath를 설정합니다.
                recommend.setThumbnailPath(getThumbnailPath(apiKey, locale, recommend.getContentsid()));
            }
            return recommends;
        }

        return Collections.emptyList();
    }
    
    private String getThumbnailPath(String apiKey, String locale, String contentsId) {
        // 썸네일 경로를 가져오기 위해 API 구조에 맞게 URL을 구성합니다.
        String thumbnailUrl = "https://api.cdn.visitjeju.net/photomng/thumbnailpath/201810/17/e798d53c-1c8a-4d44-a8ab-111beae96db4.gif";
        String thumbnailUrl2 = "https://api.cdn.visitjeju.net/photomng/thumbnailpath/202306/13/f3a5a142-f9b7-4950-a8ff-e4064f96d442.jpg";
        return thumbnailUrl;
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
            return response.getItems().get(0).getContentsid();
        }

        return null;
    }
    
    
    
    
}