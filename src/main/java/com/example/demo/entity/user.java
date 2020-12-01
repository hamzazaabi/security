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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

	
}
