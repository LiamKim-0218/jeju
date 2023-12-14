package com.java.jeju.jeju.region.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.jeju.jeju.home.domain.Home;
import com.java.jeju.jeju.home.domain.RepPhoto;
import com.java.jeju.jeju.region.service.RegionService;

@Controller
public class RegionController {

	@Value("${visitjeju.api.key}")
	private String VapiKey;

	@Autowired
	private RegionService regionService;

	@GetMapping("/region")
	public String regionPage(Model model) {
		String apiKey = VapiKey;
		String locale = "ko";
		int page = 26;

		List<Home> touristSpots = regionService.getTouristSpots(apiKey, locale, page);

		if (touristSpots != null && !touristSpots.isEmpty()) {
			for (Home spot : touristSpots) {
				RepPhoto repPhoto = spot.getRepPhoto();
				if (repPhoto != null) {
					String thumbnailUrl = repPhoto.getPhotoid().getThumbnailpath();
					spot.setThumbnailUrl(thumbnailUrl);
				}
			}

			model.addAttribute("touristSpots", touristSpots);
		}

		return "/pages/region";
	}

	@GetMapping("/jejuRegion")
	public String regionJejupage(Model model) {
		String apiKey = VapiKey;
		String locale = "ko";
		int page = 30;

		List<Home> touristSpots = regionService.getTouristSpots(apiKey, locale, page);

		if (touristSpots != null && !touristSpots.isEmpty()) {
			for (Home spot : touristSpots) {
				RepPhoto repPhoto = spot.getRepPhoto();
				if (repPhoto != null) {
					String thumbnailUrl = repPhoto.getPhotoid().getThumbnailpath();
					spot.setThumbnailUrl(thumbnailUrl);
				}
			}

			model.addAttribute("touristSpots", touristSpots);
		}

		return "/pages/jejuRegion";
	}

}