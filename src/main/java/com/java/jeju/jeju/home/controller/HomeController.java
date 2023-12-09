package com.java.jeju.jeju.home.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.jeju.jeju.home.domain.Home;
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
            model.addAttribute("touristSpots", touristSpots);
        }
        
        if (touristSpots != null) {
            model.addAttribute("touristSpots", touristSpots);
            model.addAttribute("thumbnailBaseUrl", "/path/to/your/thumbnail/base"); // 실제 썸네일이 저장된 기본 URL
        }

        return "/pages/home"; 
    }
}