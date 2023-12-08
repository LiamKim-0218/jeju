package com.java.jeju.jeju.ApiTest.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VisitJejuService {

    @Value("${visitjeju.api.url}")
    private String apiUrl;

    @Value("${visitjeju.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public VisitJejuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    

    public String getVisitJejuData(String cid) {
        try {
            String url = String.format("%s?apiKey=%s&locale=kr&page=1&cid=%s", apiUrl, apiKey, cid);
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("API Response: " + response);  // 디버깅용 로그
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
 // 관광지 목록을 얻는 메서드
    public List<String> getTouristSpotIds() {
        List<String> touristSpotIds = new ArrayList<>();
        
        try {
            String url = String.format("%s?apiKey=%s&locale=kr&page=1&cid=%s", apiUrl, apiKey, "your-seogwipo-cid");
            String jsonResponse = restTemplate.getForObject(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            JsonNode itemsNode = rootNode.path("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode contentsIdNode = itemNode.path("contentsid");
                    if (contentsIdNode != null && contentsIdNode.isTextual()) {
                        touristSpotIds.add(contentsIdNode.asText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return touristSpotIds;
    }

    // 각 관광지의 상세 정보를 얻는 메서드
    public String getTouristSpotDetails(String contentsId) {
        try {
            String url = String.format("%s?apiKey=%s&locale=kr&page=1&cid=%s", apiUrl, apiKey, contentsId);
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
 // 페이징을 고려하여 모든 관광지 정보를 얻는 메서드
    public List<String> getAllTouristSpotDetails() {
        List<String> allTouristSpotDetails = new ArrayList<>();
        int pageCount = getPageCount();

        for (int currentPage = 1; currentPage <= pageCount; currentPage++) {
            try {
                String url = String.format("%s?apiKey=%s&locale=kr&page=%d&cid=%s", apiUrl, apiKey, currentPage, "your-seogwipo-cid");
                String jsonResponse = restTemplate.getForObject(url, String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonResponse);

                JsonNode itemsNode = rootNode.path("items");
                if (itemsNode.isArray()) {
                    for (JsonNode itemNode : itemsNode) {
                        allTouristSpotDetails.add(itemNode.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return allTouristSpotDetails;
    }

    // 전체 페이지 수를 얻는 메서드
    private int getPageCount() {
        try {
            String url = String.format("%s?apiKey=%s&locale=kr&page=1&cid=%s", apiUrl, apiKey, "your-seogwipo-cid");
            String jsonResponse = restTemplate.getForObject(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            return rootNode.path("pageCount").asInt();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    

    // 서귀포 지역의 관광지 목록 조회
    public List<String> getTouristSpotsInSeogwipo() {
    	try {
            // 실제 API를 호출하여 데이터를 가져오도록 수정
            String url = String.format("%s?apiKey=%s&locale=kr&page=1&cid=%s", apiUrl, apiKey, "CONT_000000000500349");
            String response = restTemplate.getForObject(url, String.class);

            // 파싱하여 관광지 목록을 추출
            List<String> touristSpots = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode itemsNode = rootNode.path("items");

            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode contentsIdNode = itemNode.path("contentsid");
                    if (contentsIdNode.isTextual()) {
                        touristSpots.add(contentsIdNode.asText());
                    }
                }
            }

            return touristSpots;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();  // 예외 발생 시 빈 목록 반환
        }
    }

    // 서귀포 지역의 첫 번째 관광지의 이미지 경로 가져오기
    public String getFirstTouristSpotImageInSeogwipo() {
        List<String> touristSpots = getTouristSpotsInSeogwipo();
        if (!touristSpots.isEmpty()) {
            return getVisitJejuImageData(touristSpots.get(0));
        }
        return "No image found";
    }

    // 콘텐츠 ID로 VisitJeju 이미지 경로 가져오기
    public String getVisitJejuImageData(String cid) {
        try {
            String url = String.format("%s?apiKey=%s&locale=kr&page=1&cid=%s", apiUrl, apiKey, cid);
            String jsonResponse = restTemplate.getForObject(url, String.class);
            System.out.println("Image API Response: " + jsonResponse);  // 디버깅용 로그

            // Parse JSON response
            String imagePath = "";
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonResponse);

                JsonNode itemsNode = rootNode.path("items");
                if (itemsNode.isArray() && itemsNode.size() > 0) {
                    JsonNode repPhotoNode = itemsNode.get(0).path("repPhoto");
                    if (repPhotoNode != null && repPhotoNode.has("imgpath")) {
                        imagePath = repPhotoNode.get("imgpath").asText();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return imagePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "No image found";
        }
    }
}