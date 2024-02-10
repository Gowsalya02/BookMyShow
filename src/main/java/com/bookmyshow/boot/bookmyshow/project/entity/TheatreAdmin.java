package com.bookmyshow.boot.bookmyshow.project.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class TheatreAdmin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdminId;
	private String theatreAdminName;
	private String theatreAdminMail;
	private String theatreAdminPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Theatre theatre;
	

}
