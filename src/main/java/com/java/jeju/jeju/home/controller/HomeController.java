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
		int page = 20;

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
	    String apiKey = VapiKey;
	    String locale = "ko";
	    int page = 20;

	    try {
	        Home specificSpot = homeService.getTouristSpotDetails(apiKey, locale, page, contentsId);
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
	            return "pages/detail";
	        }
	        // 수정된 부분 끝

	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	

}