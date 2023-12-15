package com.java.jeju.jeju.recommend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.jeju.jeju.home.domain.Home;
import com.java.jeju.jeju.home.domain.RepPhoto;
import com.java.jeju.jeju.recommend.service.RecommendService;

@Controller
public class RecommendController {

	@Value("${visitjeju.api.key}")
	private String VapiKey;

	@Autowired
	private RecommendService recommendService;

	@GetMapping("/recommend")
	public String recommendPage(Model model) {
		String apiKey = VapiKey;
		String locale = "ko";
		int page = 25;

		List<Home> touristSpots = recommendService.getTouristSpots(apiKey, locale, page);

		if (touristSpots != null) {
			for (Home spot : touristSpots) {
				RepPhoto repPhoto = spot.getRepPhoto();
				if (repPhoto != null) {
					String thumbnailUrl = repPhoto.getPhotoid().getThumbnailpath();
					spot.setThumbnailUrl(thumbnailUrl);
				}
			}

			model.addAttribute("touristSpots", touristSpots);
		}

		return "/pages/recommend";
	}
	
	@GetMapping("/recommendDetail/{contentsId}")
	public String showDetailPage(@PathVariable(name = "contentsId") String contentsId, Model model) {
	    String apiKey = VapiKey;
	    String locale = "ko";
	    int page = 25;

	    try {
	        // 수정된 부분 시작
	        Home specificSpot = recommendService.getTouristSpotDetails(apiKey, locale, page, contentsId);
	        if (specificSpot != null) {
	            RepPhoto repPhoto = specificSpot.getRepPhoto();
	            if (repPhoto != null) {
	                String thumbnailUrl = repPhoto.getPhotoid().getThumbnailpath();
	                specificSpot.setThumbnailUrl(thumbnailUrl);
	            }
	            if (repPhoto != null) {
	                String imgpathUrl = repPhoto.getPhotoid().getImgpath();
	                specificSpot.setImgpathUrl(imgpathUrl);
	            }
	            model.addAttribute("spot", specificSpot);
	            return "pages/recommendDetail";
	        }
	        // 수정된 부분 끝

	        // 만약 해당 contentsId에 맞는 관광지가 없다면 에러 페이지를 반환
	        return "pages/error";
	    } catch (Exception e) {
	        // 예외 발생 시 로그 출력 또는 적절한 처리 추가
	        e.printStackTrace();
	        return "pages/error";
	    }
	}
	
}