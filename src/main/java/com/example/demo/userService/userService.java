package com.example.demo.userService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user;
import com.example.demo.repository.userRepository;

@Service
public class userService {
	@Autowired
	private userRepository userRepository;

	public Optional<user> getuserByemail(String email) {
		return userRepository.findByEmail(email);

	}
}
