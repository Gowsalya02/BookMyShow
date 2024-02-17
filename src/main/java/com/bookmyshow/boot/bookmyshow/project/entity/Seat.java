package com.bookmyshow.boot.bookmyshow.project.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Seat 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;
	@Positive
	private int seatNumber;
	private SeatType seatType;
 

	
}
