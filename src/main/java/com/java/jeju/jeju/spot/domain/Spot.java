package com.java.jeju.jeju.spot.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String introduction;
    
    private String alltag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAlltag() {
		return alltag;
	}
	public void setAlltag(String alltag) {
		this.alltag = alltag;
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
	private String address;
	private String roadaddress;

	
}