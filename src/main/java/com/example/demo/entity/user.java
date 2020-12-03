package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.auditing.AuditClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@EntityListeners(AuditingEntityListener.class)
public class user extends AuditClass<String>{
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String password;
	private boolean Activate;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActivate() {
		return Activate;
	}
	public void setActivate(boolean activate) {
		Activate = activate;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public user(Long id, String email, String password, boolean activate, List<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		Activate = activate;
		this.roles = roles;
	}
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}
