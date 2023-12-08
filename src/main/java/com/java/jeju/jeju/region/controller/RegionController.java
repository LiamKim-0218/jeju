package com.java.jeju.jeju.region.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegionController {

	@GetMapping("/region")
	public String regionPage() {

		return "/pages/region";
	}
	
	@GetMapping("/jejuRegion")
	public String regionJejupage() {

		return "/pages/jejuRegion";
	}

}