package com.java.jeju.jeju.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.jeju.jeju.community.dao.CommunityDao;
import com.java.jeju.jeju.community.domain.Community;


@Service
public class CommunityService {
	@Autowired
	CommunityDao communityDao;
	
	public void add(Community board) {
		communityDao.add(board);
	}
	
	public Community get(int id) {
		return communityDao.get(id);
	}
	
	public List<Community> getAll(int page, int count){
		return communityDao.getAll((page - 1) * count, count);
	}
	
	public int getPageCount(int count) {
//		한 페이지에서 목록을 몇개 출력할 것인가? 5
//		글 1 => 페이지 1 <= (1 - 1) / 5 + 1
//		글 10 => 페이지 2 <= (10 - 1) / 5 + 1
//		글 11 => 페이지 3 <= (11 - 1) / 5 + 1
//		글 15 => 페이지 3 <= (15 - 1) / 5 + 1
//		int / int => int?
		return (communityDao.getCount() - 1) / count + 1;
	}
}