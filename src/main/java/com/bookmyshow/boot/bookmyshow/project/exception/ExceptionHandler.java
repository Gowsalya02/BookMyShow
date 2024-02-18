package com.bookmyshow.boot.bookmyshow.project.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
			
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminNotFound(AdminNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("admin not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingNotDone(BookingNotDone ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("booking not done for the given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> movieNotFound(MovieNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("movie not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> reviewNotFound(ReviewNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("review not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> screenNotFound(ScreenNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("screen not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreNotFound(TheatreNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("theatre not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreAdminNotFound(TheatreAdminNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("theatre admin not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("user not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> seatNotFound(SeatNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("seat object not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketNotFound(TicketNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("Ticket not found for given id");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex)
	{
		ResponseStructure<Object> structure=new ResponseStructure<Object>();
		Map<String, String>hashMap=new HashMap<String, String>();
		
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) 
		{
			String field=violation.getPropertyPath().toString();
			String message=violation.getMessage();
			hashMap.put(field, message);
		}
		
		structure.setMessage("add proper details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(hashMap);
		return new ResponseEntity<Object>(structure,HttpStatus.FORBIDDEN);
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidStatusType(InvalidStatusType ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setMessage("status type is invalid");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
