package com.creatorhub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creatorhub.demo.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}