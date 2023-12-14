package com.java.jeju.jeju.recommend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}