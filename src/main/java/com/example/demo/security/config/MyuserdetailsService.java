package com.example.demo.security.config;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user;
import com.example.demo.userService.userService;
@Service
public class MyuserdetailsService implements UserDetailsService {
	Logger log= org.slf4j.LoggerFactory.getLogger(MyuserdetailsService.class);
	@Autowired
	private userService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<user> user = userService.getuserByemail(username);
		if(user.isPresent())
		{
			
			ArrayList<GrantedAuthority> listRoles = new ArrayList<GrantedAuthority>();
			user.get().getRoles().forEach(s->
			{
				listRoles.add(new SimpleGrantedAuthority(s.getRole()));
			});
				log.info("kkk");
			return new User(user.get().getEmail(),user.get().getPassword(),listRoles);
		
		}
		throw new UsernameNotFoundException("user nexcite pas");
		
			

		
	}

}
