package com.creatorhub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.creatorhub.demo.model.Opportunity;
import com.creatorhub.demo.repository.OpportunityRepository;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@CrossOrigin(origins = "*")
public class OpportunityController {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @PostMapping
    public Opportunity saveOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    @GetMapping
    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }
}