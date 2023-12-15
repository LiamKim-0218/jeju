package com.java.jeju.jeju.spot.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.jeju.jeju.spot.domain.Spot;

public interface SpotRepository extends JpaRepository<Spot, Long> {

}