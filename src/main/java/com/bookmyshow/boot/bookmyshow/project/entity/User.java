package com.bookmyshow.boot.bookmyshow.project.entity;

import java.util.List;


import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private String userName;
	private String userMail;
	private String userPassword;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Booking> bookingList;
	
}
