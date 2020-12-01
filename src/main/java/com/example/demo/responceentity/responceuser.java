package com.example.demo.responceentity;

import java.util.List;

import com.example.demo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class responceuser {
	private String name ;
	private String email;
	private List<Role> Roles;

}
