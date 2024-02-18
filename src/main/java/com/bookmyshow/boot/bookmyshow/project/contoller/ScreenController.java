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

import com.bookmyshow.boot.bookmyshow.project.entity.Screen;
import com.bookmyshow.boot.bookmyshow.project.service.ScreenService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("screen")
public class ScreenController 
{
	@Autowired
	ScreenService screenService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(@Valid @RequestBody Screen screen) 
	{
		return screenService.saveScreen(screen);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Screen>> findScreen(@Valid @RequestParam int screenId) 
	{
		return screenService.findScreen(screenId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(@Valid @RequestParam int screenId) 
	{
		return screenService.deleteScreen(screenId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(@Valid @RequestBody Screen screen,@Valid @RequestParam int screenId) 
	{
		return screenService.updateScreen(screen,screenId);	
	}
	
	@GetMapping("all")
	public List<Screen> findAllScreen()
	{
		return screenService.findAllScreens();
	}

	@PutMapping("assigntheatre")
	public ResponseEntity<ResponseStructure<Screen>> addTheatreToScreen(@Valid @RequestParam int screenId,@Valid @RequestParam int theatreId )
	{
		return screenService.addTheatreToScreen(screenId, theatreId);
	}
	
	@PutMapping("assignstatus")
	public ResponseEntity<ResponseStructure<Screen>> addStatusToScreen(int screenId,int status)
	{
		return screenService.addStatusToScreen(screenId, status);
	}
}
