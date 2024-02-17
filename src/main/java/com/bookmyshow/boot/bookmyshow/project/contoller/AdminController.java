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

import com.bookmyshow.boot.bookmyshow.project.dto.AdminDto;
import com.bookmyshow.boot.bookmyshow.project.entity.Admin;
import com.bookmyshow.boot.bookmyshow.project.service.AdminService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@Valid @RequestBody Admin admin) 
	{
		return adminService.saveAdmin(admin);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@Valid @RequestParam  int adminId) 
	{
		return adminService.findAdmin(adminId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@Valid @RequestParam int adminId) 
	{
		return adminService.deleteAdmin(adminId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@Valid @RequestBody Admin admin,@Valid @RequestParam int adminId) 
	{
		return (ResponseEntity<ResponseStructure<AdminDto>>) adminService.updateAdmin(admin,adminId);	
	}
	
	@GetMapping("all")
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmins()
	{
		return adminService.findAllAdmins();
	}
	
	@GetMapping("adminlogin")
	public ResponseEntity<ResponseStructure<AdminDto>> adminLogin(@Valid String adminMail,@Valid String adminPassword)
	{
		return adminService.adminLogin(adminMail, adminPassword);
	}

	@PutMapping("assgintheatre")
	public ResponseEntity<ResponseStructure<AdminDto>> addTheatreToAdmin(@Valid @RequestParam String adminMail,@Valid @RequestParam String adminPassword,@Valid @RequestParam int theatreId)
	{
		return adminService.addTheatreToAdmin(adminMail, adminPassword, theatreId);
	}
	
}
