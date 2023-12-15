package com.java.jeju.jeju.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ErrorController {
	
	@GetMapping("/errors")
	public String errorPage() {
		
		return "pages/errors";
	}
}