package com.java.jeju.jeju.community.controller;


import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.java.jeju.jeju.community.domain.Community;
import com.java.jeju.jeju.community.service.CommunityService;

import jakarta.servlet.http.HttpSession;


@Controller
public class CommunityController {
	
	@Autowired
	CommunityService communityService;
	int count = 5;

	@GetMapping("/community")
	public String communityMainPage(Model model, @RequestParam Map<String, String> data) {
		int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
		model.addAttribute("title", "게시판");
		model.addAttribute("path", "/Community/index");
		model.addAttribute("content", "CommunityFragment");
		model.addAttribute("contentHead", "CommunityFragmentHead");
		model.addAttribute("list", communityService.getAll(page, count));
		model.addAttribute("pageCount", communityService.getPageCount(count));
		return "/pages/community";
	}

	@PostMapping("/add")
	public String add(@RequestParam Map<String, String> data, HttpSession session) {
		if (session.getAttribute("userName") != null) {
			String tempContent = data.get("content");
			tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
			tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
			communityService.add(new Community(data.get("title"), tempContent,
					Integer.parseInt(session.getAttribute("userId").toString())));
		}

		return "redirect:/";
	}

	@GetMapping("/notice")
	public String noticePage(Model model) {
		model.addAttribute("title", "공지사항");
		model.addAttribute("path", "/Community/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		return "/pages/community";
	}

	@GetMapping("/Community/{CommunityId}")
	public String itemPage(Model model, @PathVariable("CommunityId") int CommunityId) {
		Community Community = communityService.get(CommunityId);

		System.out.println(Community.getContent());

		model.addAttribute("title", Community.getTitle());
		model.addAttribute("path", "/Community/item");
		model.addAttribute("content", "CommunityItemFragment");
		model.addAttribute("contentHead", "CommunityItemFragmentHead");
		Community.setContent(Community.getContent().replace("\n", "<br />"));
		model.addAttribute("Community", Community);

		return "/pages/community";
	}

	@PostMapping("/upload")
	@ResponseBody
	public ModelMap uploadImage(MultipartHttpServletRequest req) {
		ModelMap model = new ModelMap();
		try {
			MultipartFile uploadFile = req.getFile("upload");
			String originName = uploadFile.getOriginalFilename();
//			String[] tempNames = originName.split(".");
			
//			String ext = originName.substring(originName.indexOf("."));
			String[] tempNames = originName.split("[.]");
			String ext = "." + tempNames[tempNames.length - 1];
			String randomName = UUID.randomUUID() + ext;
			String savePath = "C:\\Users\\namkyun\\Desktop\\workspace\\Java\\Community\\src\\main\\resources\\static\\imgs\\"
					+ randomName;
			String uploadUrl = "/imgs/" + randomName;
			File file = new File(savePath);
			uploadFile.transferTo(file);
			
			model.addAttribute("uploaded", true);
			model.addAttribute("url",uploadUrl);
//			System.out.println(uploadFile.getOriginalFilename());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
}
