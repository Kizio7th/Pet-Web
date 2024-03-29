package com.example.boss.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	
	@NotEmpty(message = "Password should not be empty")
	private String password;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id, @NotEmpty String name, @NotEmpty(message = "Email should not be empty") @Email String email,
			@NotEmpty(message = "Password should not be empty") String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
}
