package com.creatorhub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.creatorhub.demo.model.profile;

public interface ProfileRepository extends JpaRepository<profile, Long> {
}