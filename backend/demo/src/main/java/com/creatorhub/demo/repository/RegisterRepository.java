package com.creatorhub.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creatorhub.demo.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    Optional<Register> findByEmail(String email);
}