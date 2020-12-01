package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.user;

public interface userRepository extends JpaRepository<user, Long> {
	public Optional<user> findByEmail(String email);

}
