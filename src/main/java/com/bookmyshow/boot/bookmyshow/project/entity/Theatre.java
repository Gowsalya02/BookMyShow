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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Component
@Getter
@Setter
public class Theatre
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreId;
	@NotBlank
	@NotNull
	private String theatreLocation;
	@NotBlank
	@NotNull
	private String theatreName;
	@OneToOne(cascade = CascadeType.ALL)
	private TheatreAdmin theatreAdmin;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Screen> screenList;
}
