package com.example.demo.authenticationService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user;
import com.example.demo.requestentity.Logrequest;
import com.example.demo.responceentity.responceuser;
import com.example.demo.security.config.MyuserdetailsService;
import com.example.demo.security.config.Token;
import com.example.demo.userService.userService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class authService {
	Logger log = org.slf4j.LoggerFactory.getLogger(authService.class);
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private userService userService;
	@Autowired
	private Token token;
	@Autowired
	private MyuserdetailsService MyuserdetailsService;
	@SuppressWarnings("rawtypes")
	public String login(Logrequest logForm) throws Exception {
		Map<String, Object> claims = new HashMap<>();
		if (userService.getuserByemail(logForm.getEmail()).isEmpty())
			throw new NullPointerException("user n'excte pas");

		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(logForm.getEmail(), logForm.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);

		} catch (BadCredentialsException e) {
			throw new Exception("verifier votre mots ou de pass");
		}
		Optional<user> u = userService.getuserByemail(logForm.getEmail());
		UserDetails userDetails =(UserDetails) MyuserdetailsService.loadUserByUsername(logForm.getEmail());
		String jwt =token.generateToken(userDetails);
		log.info(jwt + "/////" + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

		return jwt;

	}

}
