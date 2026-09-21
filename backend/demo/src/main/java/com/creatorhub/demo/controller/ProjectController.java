package com.creatorhub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.creatorhub.demo.model.Project;
import com.creatorhub.demo.repository.ProjectRepository;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }
}