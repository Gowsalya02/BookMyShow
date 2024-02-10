package com.bookmyshow.boot.bookmyshow.project.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Theatre 
{
	private int theatreId;
	private String theatreLocation;
	private String theatreName;
	@OneToOne(cascade = CascadeType.ALL)
	private TheatreAdmin theatreAdmin;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Screen> screenList;
}
