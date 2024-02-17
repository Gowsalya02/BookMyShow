package com.bookmyshow.boot.bookmyshow.project.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Entity
public class Ticket 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketNumber;
	@Positive
	private int ticketCount;
	@Positive
	private double ticketPrice;
	@NotBlank
	@NotNull
	private String movieName;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seat>seatList;
	@OneToOne(cascade = CascadeType.ALL)
	private Booking booking;
	
	
	
}
