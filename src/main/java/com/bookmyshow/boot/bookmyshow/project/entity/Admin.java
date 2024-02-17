package com.bookmyshow.boot.bookmyshow.project.entity;

import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Admin
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotNull
	@NotBlank
	private String adminName;
	@Email
	@NotBlank
	@NotNull
	private String adminMail;
	@NotBlank
	@NotNull
	@Pattern(regexp = "^(?=.[a-z])(?=.[A)(?=.*\\d)(?=.*[@$!%#?&]){8,}$ ")
	private String adminPassword;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> theatreList;

}
