package com.bookmyshow.boot.bookmyshow.project.entity;

import java.util.List;


import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Entity
public class User 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotBlank
	@NotNull
	private String userName;
	@NotBlank
	@NotNull
	@Email
	private String userMail;
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.[a-z])(?=.[A)(?=.*\\d)(?=.*[@$!%#?&^]){8,}$ ", message="Invalid password")
	private String userPassword;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Booking> bookingList;
	
}
