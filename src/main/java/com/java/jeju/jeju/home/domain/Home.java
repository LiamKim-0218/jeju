package com.java.jeju.jeju.home.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Home {
	private String alltag;
	private String contentsid;
	private Contentscd contentscd;
	private String title;
	private Region1cd region1cd;
	private Region2cd region2cd;
	private String address;
	private String roadaddress;
	private String tag;

	public String getAlltag() {
		return alltag;
	}

	public void setAlltag(String alltag) {
		this.alltag = alltag;
	}

	public String getContentsid() {
		return contentsid;
	}

	public void setContentsid(String contentsid) {
		this.contentsid = contentsid;
	}

	public Contentscd getContentscd() {
		return contentscd;
	}

	public void setContentscd(Contentscd contentscd) {
		this.contentscd = contentscd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Region1cd getRegion1cd() {
		return region1cd;
	}

	public void setRegion1cd(Region1cd region1cd) {
		this.region1cd = region1cd;
	}

	public Region2cd getRegion2cd() {
		return region2cd;
	}

	public void setRegion2cd(Region2cd region2cd) {
		this.region2cd = region2cd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoadaddress() {
		return roadaddress;
	}

	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public RepPhoto getRepPhoto() {
		return repPhoto;
	}

	public void setRepPhoto(RepPhoto repPhoto) {
		this.repPhoto = repPhoto;
	}

	private String introduction;
	private Double latitude;
	private Double longitude;
	private String postcode;
	private String phoneno;
	private RepPhoto repPhoto;

}