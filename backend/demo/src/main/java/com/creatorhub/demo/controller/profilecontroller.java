package com.creatorhub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.creatorhub.demo.model.profile;
import com.creatorhub.demo.repository.ProfileRepository;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class profilecontroller {

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping
    public profile saveProfile(@RequestBody profile profile) {
        return profileRepository.save(profile);
    }
}