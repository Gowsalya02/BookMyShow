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

import com.bookmyshow.boot.bookmyshow.project.entity.Booking;
import com.bookmyshow.boot.bookmyshow.project.service.BookingService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@RequestMapping("booking")
@RestController
public class BookingController 
{
	@Autowired
	BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking) 
	{
		return bookingService.saveBooking(booking);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Booking>> findBooking(@RequestParam int bookingId) 
	{
		return bookingService.findBooking(bookingId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(@RequestParam int bookingId) 
	{
		return bookingService.deleteBooking(bookingId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@RequestBody Booking booking,@RequestParam int bookingId) 
	{
		return bookingService.updateBooking(booking,bookingId);	
	}
	
	@GetMapping("all")
	public List<Booking> findAllbooking()
	{
		return bookingService.findAllBookings();
	}

}