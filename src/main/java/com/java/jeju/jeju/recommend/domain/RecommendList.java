package com.java.jeju.jeju.recommend.domain;

import java.util.List;

public class RecommendList {
    private List<Recommend> items; // Recommend 객체의 리스트

    public List<Recommend> getItems() {
        return items;
    }

    public void setItems(List<Recommend> items) {
        this.items = items;
    }
}