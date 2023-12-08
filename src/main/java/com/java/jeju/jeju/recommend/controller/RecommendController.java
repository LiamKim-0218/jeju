package com.java.jeju.jeju.recommend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.jeju.jeju.recommend.domain.Recommend;
import com.java.jeju.jeju.recommend.service.RecommendService;

@Controller
public class RecommendController {

	private final RecommendService recommendService;

	public RecommendController(RecommendService recommendService) {
		this.recommendService = recommendService;
	}

	@Value("${visitjeju.api.key}")
	private String apiKey;

	@GetMapping("/recommend")
	public String recommendPage(Model model) {
		// 서귀포의 콘텐츠 ID 가져오기
		String seogwipoContentsId = recommendService.getSeogwipoContentsId(apiKey, "kr", 1, "");

		if (seogwipoContentsId != null) {
			// 얻은 콘텐츠 ID를 사용하여 서귀포 관련 추천 정보 가져오기
			List<Recommend> seogwipoRecommends = recommendService.getRecommends(apiKey, "kr", 1, seogwipoContentsId);

			model.addAttribute("seogwipoRecommends", seogwipoRecommends);
		}

		// 제주시의 콘텐츠 ID 가져오기
		String jejuContentsId = recommendService.getJejuContentsId(apiKey, "kr", 1, "");

		if (jejuContentsId != null) {
			// 얻은 콘텐츠 ID를 사용하여 제주시 관련 추천 정보 가져오기
			List<Recommend> jejuRecommends = recommendService.getRecommends(apiKey, "kr", 1, jejuContentsId);

			model.addAttribute("jejuRecommends", jejuRecommends);
		}

		// 기존의 CONT_000000000500349에 대한 추천 정보 가져오기
		List<Recommend> recommends = recommendService.getRecommends(apiKey, "kr", 1, "CONT_000000000500349");

		model.addAttribute("recommends", recommends);

		return "/pages/recommend";
	}
}