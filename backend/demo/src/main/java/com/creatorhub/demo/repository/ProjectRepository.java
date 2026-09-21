package com.creatorhub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creatorhub.demo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}