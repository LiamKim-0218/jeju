package com.java.jeju.jeju.community.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Community {
	private int id;
	@NonNull
	private String title;
	@NonNull
	private String content;
	private int views = 0;
	private int likes = 0;
	private int hates = 0;
	private Timestamp createdAt;
	private boolean isWithdrew = false;
	@NonNull
	private int userId;
	private String userName;
	private String userGitAddress;
}