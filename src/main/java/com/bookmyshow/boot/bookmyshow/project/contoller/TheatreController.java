package com.bookmyshow.boot.bookmyshow.project.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;
import com.bookmyshow.boot.bookmyshow.project.service.TheatreService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("theatre")
public class TheatreController 
{
	@Autowired
	TheatreService theatreService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@Valid @RequestBody Theatre theatre) 
	{
		return theatreService.saveTheatre(theatre);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(@Valid @RequestParam int theatreId) 
	{
		return theatreService.findTheatre(theatreId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre( @Valid@RequestParam int theatreId) 
	{
		return theatreService.deleteTheatre(theatreId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@Valid @RequestBody Theatre theatre,@Valid @RequestParam int theatreId) 
	{
		return theatreService.updateTheatre(theatre,theatreId);	
	}
	
	@GetMapping("all")
	public List<Theatre> findAllTheatre()
	{
		return theatreService.findAllTheatres();
	}

}
