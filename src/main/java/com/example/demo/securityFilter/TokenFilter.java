package com.example.demo.securityFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.security.config.MyuserdetailsService;
import com.example.demo.security.config.Token;

@Service
public class TokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

	@Autowired
	private MyuserdetailsService MyUserDetailsService;

	@Autowired
	private Token ut;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		//logger.info("Authorization   " + header);
		String token = null;
		String username = null;
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			logger.info(token);
			username = ut.getUsernameFromToken(token);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			logger.info("0");
			UserDetails userdetails = (UserDetails) MyUserDetailsService.loadUserByUsername(username);
			logger.info(userdetails.getAuthorities().toString());
			if (ut.validateToken(token, userdetails)) {
				UsernamePasswordAuthenticationToken Authentication = new UsernamePasswordAuthenticationToken(
						userdetails.getUsername(), userdetails.getPassword(),userdetails.getAuthorities());
				logger.info("2");
				Authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(Authentication);
				logger.info("3");
			}

		}
		filterChain.doFilter(request, response);

	}

}
