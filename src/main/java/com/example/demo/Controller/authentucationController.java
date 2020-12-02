package com.example.demo.Controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.authenticationService.authService;
import com.example.demo.requestentity.Logrequest;

@RestController
public class authentucationController {
	@Autowired
	private authService authService;

	@PostMapping("/auth")
	public String logint(@RequestBody Logrequest logForm) throws Exception {
	
		return authService.login(logForm);
	}

	@GetMapping("/suprimer")

	public String userconecte()

	{

		return "get";
	}
	@GetMapping("/user")

	public String sup()

	{

		return "sup";
	}
	
}
