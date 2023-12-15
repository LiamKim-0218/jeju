package com.java.jeju.jeju.recommend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.java.jeju.jeju.home.domain.ApiResponse;
import com.java.jeju.jeju.home.domain.Home;
import com.java.jeju.jeju.recommend.domain.Recommend;
import com.java.jeju.jeju.recommend.domain.RecommendList;

@Service
public class RecommendService {

	@Value("${visitjeju.api.url}")
	private String apiUrl;
	@Value("${visitjeju.api.key}")
	private String apiKey;

	public List<Home> getTouristSpots(String apiKey, String locale, int page) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl).queryParam("apiKey", apiKey)
				.queryParam("locale", locale).queryParam("page", page);

		String url = builder.toUriString();

		RestTemplate restTemplate = new RestTemplate();
		ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

		if (response != null && response.getItems() != null) {
			return response.getItems();
		}

		return Collections.emptyList();
	}
	
	public Home getTouristSpotDetails(String apiKey, String locale, int page, String contentsId) {
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
	            .queryParam("apiKey", apiKey)
	            .queryParam("locale", locale)
	            .queryParam("page", page)
	            .queryParam("contentsId", contentsId);

	    String url = builder.toUriString();

	    RestTemplate restTemplate = new RestTemplate();
	    ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

	    // API로부터 상세 정보를 가져오는 로직을 구현
	    // 예를 들어, response에서 필요한 정보를 추출하거나 다른 API 호출을 통해 상세 정보를 가져오는 등의 작업이 이루어져야 합니다.

	    // 여기서는 가상의 로직으로, 해당하는 contentsId를 찾으면 해당 관광지를 반환하도록 작성
	    List<Home> allTouristSpots = getTouristSpots(apiKey, locale, 25);

	    for (Home spot : allTouristSpots) {
	        if (spot.getContentsid().equals(contentsId)) {
	            return spot;
	        }
	    }

	    // 해당하는 contentsId를 찾지 못한 경우 null을 반환
	    return null;
	}
    
    
}