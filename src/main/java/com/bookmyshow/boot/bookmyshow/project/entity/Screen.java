package com.bookmyshow.boot.bookmyshow.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
@Entity
public class Screen 
{
	@Id
	private int screenNumber;
	private LocalTime showTime;
	private LocalDate showDate;
	private int totalNoOfSheet;
	@OneToOne(cascade = CascadeType.ALL)
	private Movie movieName;
	private Status statusOfShow;

}
