package com.bookmyshow.boot.bookmyshow.project.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Component
@Getter
@Setter
public class TheatreAdmin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdminId;
	@NotNull
	@NotBlank
	private String theatreAdminName;
	@NotBlank
	@NotNull
	@Email
	private String theatreAdminMail;
//	@Pattern(regexp = "^(?=.[a-z])(?=.[A)(?=.*\\d)(?=.*[@$!%#?&^]){8,}$ ", message="Invalid password")
	private String theatreAdminPassword;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Theatre theatre;
	

}
