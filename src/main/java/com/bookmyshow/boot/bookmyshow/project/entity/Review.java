package com.bookmyshow.boot.bookmyshow.project.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private String review;
	private double rating;
	private int userId;
	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movie;

}
