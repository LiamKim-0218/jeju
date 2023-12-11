package com.java.jeju.jeju.home.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.jeju.jeju.home.domain.Home;
import com.java.jeju.jeju.home.domain.RepPhoto;
import com.java.jeju.jeju.home.service.HomeService;
import com.java.jeju.jeju.recommend.domain.Recommend;

@Controller
public class HomeController {

	@Value("${visitjeju.api.key}")
	private String VapiKey;

	@Autowired
	private HomeService homeService;

	@GetMapping("/")
	public String homeMainpage(Model model) {
		String apiKey = VapiKey;
		String locale = "ko";
		int page = 2;

		List<Home> touristSpots = homeService.getTouristSpots(apiKey, locale, page);

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

		if (touristSpots != null) {
			// 필터링된 스팟들을 추가할 리스트
			List<Home> filteredSpots = new ArrayList<>();

			for (Home spot : touristSpots) {
				String contentsType = spot.getContentscd().getLabel(); // "음식점", "쇼핑" 등이 들어있는 필드
				String regionLabel = spot.getRegion1cd().getLabel(); // "서귀포시" 또는 "제주시" 등이 들어있는 필드

				// 원하는 조건에 따라 필터링
				if (("음식점".equals(contentsType) || "쇼핑".equals(contentsType))
						&& ("서귀포시".equals(regionLabel) || "제주시".equals(regionLabel))) {
					filteredSpots.add(spot);
				}
			}

			model.addAttribute("filteredSpots", filteredSpots);
		}

		return "/pages/home";
	}
}