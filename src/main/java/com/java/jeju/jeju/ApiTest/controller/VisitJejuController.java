package com.java.jeju.jeju.ApiTest.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.jeju.jeju.ApiTest.service.VisitJejuService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class VisitJejuController {

	private final VisitJejuService visitJejuService;

	public VisitJejuController(VisitJejuService visitJejuService) {
		this.visitJejuService = visitJejuService;
	}
	
	@Data
	@AllArgsConstructor
	public static class TouristSpot {
	    private String title;
	    private String address;
	    // ... 다른 필요한 정보들
	}

	@GetMapping("/allTouristSpots")
    public String getAllTouristSpots(Model model) {
        List<String> allTouristSpotDetails = visitJejuService.getAllTouristSpotDetails();

        // 추출한 관광지 정보를 적절히 처리하여 모델에 추가
        List<TouristSpot> touristSpots = processTouristSpotDetails(allTouristSpotDetails);

        model.addAttribute("touristSpots", touristSpots);

        return "allTouristSpots"; // 뷰 이름 (allTouristSpots.html)
    }

	private List<TouristSpot> processTouristSpotDetails(List<String> allTouristSpotDetails) {
	    List<TouristSpot> touristSpots = new ArrayList<>();
	    ObjectMapper objectMapper = new ObjectMapper();

	    for (String jsonDetail : allTouristSpotDetails) {
	        try {
	            JsonNode rootNode = objectMapper.readTree(jsonDetail);

	            // 적절한 방식으로 JsonNode에서 필요한 정보를 추출하여 TouristSpot 객체 생성
	            // 아래는 예시이므로 적절히 수정해야 합니다.
	            TouristSpot touristSpot = new TouristSpot(
	                rootNode.path("title").asText(),
	                rootNode.path("address").asText()
	            );
	            // ... 필요한 정보 추가

	            touristSpots.add(touristSpot);
	        } catch (Exception e) {
	            // 에러를 콘솔에 출력하고 계속 진행
	            e.printStackTrace();
	        }
	    }

	    return touristSpots;
	}


    
	@GetMapping("/visitjeju")
	public String getVisitJejuData(Model model) {
	    // 서귀포 지역의 관광지 목록을 가져옴
	    List<String> touristSpots = visitJejuService.getTouristSpotsInSeogwipo();

	    // 서귀포 지역의 첫 번째 관광지의 콘텐츠 ID로 데이터와 이미지를 가져옴
	    String firstTouristSpotData = "";
	    String firstTouristSpotImagePath = "No image found";
	    if (!touristSpots.isEmpty()) {
	        String firstTouristSpotCid = touristSpots.get(0);
	        firstTouristSpotData = visitJejuService.getVisitJejuData(firstTouristSpotCid);
	        firstTouristSpotImagePath = visitJejuService.getVisitJejuImageData(firstTouristSpotCid);
	    }

	    model.addAttribute("visitJejuData", firstTouristSpotData);
	    model.addAttribute("imagePath", firstTouristSpotImagePath);

	    return "visitjeju";
	}
}