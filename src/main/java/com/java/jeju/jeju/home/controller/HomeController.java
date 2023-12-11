package com.java.jeju.jeju.home.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.jeju.jeju.home.domain.Home;
import com.java.jeju.jeju.home.domain.RepPhoto;
import com.java.jeju.jeju.home.service.HomeService;

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
		int page = 30;

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

		return "/pages/home";
	}

	@GetMapping("/detail/{contentsId}")
	public String showDetailPage(@PathVariable(name = "contentsId") String contentsId, Model model) {
	    try {
	        Home spot = homeService.getTouristSpotDetails(contentsId);
	        model.addAttribute("spot", spot);
	        return "/pages/detail";
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "상세 정보를 불러오는 중에 오류가 발생했습니다. 에러 메시지: " + e.getMessage());
	        return "error";
	    }
	}
	

}