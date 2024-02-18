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
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
@Entity
public class Screen 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	private LocalTime showTime;
	private LocalDate showDate;
	@Positive
	private int totalNoOfSeats;
	private String movieName;
	private Status statusOfShow;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Theatre theatre;

}
