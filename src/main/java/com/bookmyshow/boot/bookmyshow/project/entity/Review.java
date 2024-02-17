package com.bookmyshow.boot.bookmyshow.project.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Component
public class Review 
{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int reviewId;
	@NotBlank
	@NotNull
	private String review;
	@NotBlank
	@NotNull
	private double rating;
	@Positive
	private int userId;
	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movie;

}
