package com.bookmyshow.boot.bookmyshow.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity	
@Component
@Getter
@Setter
public class Booking 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	@NotBlank
	@NotNull
	private String movieName;
	private LocalDate showDate;//22/07/2020
	private LocalTime showTime;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket ticket;
	private int ticketCount;
	private double totalAmount;
	
}
