package com.creatorhub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.creatorhub.demo.model.Achievement;
import com.creatorhub.demo.repository.AchievementRepository;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@CrossOrigin(origins = "*")
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @PostMapping
    public Achievement saveAchievement(@RequestBody Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    @GetMapping
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }
}