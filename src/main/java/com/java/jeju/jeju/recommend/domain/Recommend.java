package com.java.jeju.jeju.recommend.domain;

public class Recommend {
	private String contentsid;
    private String title; // 콘텐츠 제목
    private String thumbnailPath; // 이미지의 썸네일 경로
    
    
    public String getContentsid() {
        return contentsid;
    }

    public void setContentsid(String contentsid) {
        this.contentsid = contentsid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
