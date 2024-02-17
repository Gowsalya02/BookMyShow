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

import com.bookmyshow.boot.bookmyshow.project.dto.TheatreAdminDto;
import com.bookmyshow.boot.bookmyshow.project.entity.TheatreAdmin;
import com.bookmyshow.boot.bookmyshow.project.service.TheatreAdminService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("theatreadmin")
public class TheatreAdminController 
{
	@Autowired
	TheatreAdminService theatreAdminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(@Valid @RequestBody TheatreAdmin theatreAdmin) 
	{
		return theatreAdminService.saveTheatreAdmin(theatreAdmin);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findTheatreAdmin(@Valid @RequestParam int theatreAdminId) 
	{
		return theatreAdminService.findTheatreAdmin(theatreAdminId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(@Valid @RequestParam int theatreAdminId) 
	{
		return theatreAdminService.deleteTheatreAdmin(theatreAdminId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateTheatreAdmin(@Valid @RequestBody TheatreAdmin theatreAdmin,@Valid @RequestParam int theatreAdminId) 
	{
		return theatreAdminService.updateTheatreAdmin(theatreAdmin,theatreAdminId);	
	}
	
	@GetMapping("all")
	public List<TheatreAdminDto> findAllTheatreAdmin()
	{
		return theatreAdminService.findAllTheatreAdmins();
	}

	@GetMapping("tadminlogin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> theatreAdminLogin(@Valid @RequestParam String theatreAdminMail,@Valid @RequestParam String theatreAdminPassword)
	{
		return theatreAdminService.theatreAdminLogin(theatreAdminMail, theatreAdminPassword);
		
	}
	
	@PutMapping("assigntheatre")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> addTheatreByAdmin(@Valid @RequestParam String theatreAdminMail,@Valid @RequestParam String theatreAdminPassword,int theatreId)
	{
		return theatreAdminService.addTheatreByAdmin(theatreAdminMail, theatreAdminPassword, theatreId);
	}
	
	
}
