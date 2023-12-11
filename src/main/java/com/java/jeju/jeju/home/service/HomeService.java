package com.java.jeju.jeju.home.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.jeju.jeju.home.domain.ApiResponse;
import com.java.jeju.jeju.home.domain.Home;
import com.java.jeju.jeju.recommend.domain.RecommendList;

@Service
public class HomeService {

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
	
	
	public Home getTouristSpotDetails(String contentsId) {
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
	            .queryParam("apiKey", apiKey)
	            .queryParam("contentsId", contentsId);

	    String url = builder.toUriString();

	    RestTemplate restTemplate = new RestTemplate();
	    ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

	    Home spot = null;
	    if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
	        spot = response.getItems().get(0);

	        // Update: Set introduction field if not already set
	        if (spot != null && spot.getIntroduction() == null) {
	            spot.setIntroduction("상세 정보 없음"); // 또는 적절한 기본값으로 설정
	        }
	    } else {
	        System.out.println("API 응답이 비어 있거나 null입니다.");
	    }

	    return spot;
	}

}