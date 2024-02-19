package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.BookingDao;
import com.bookmyshow.boot.bookmyshow.project.dao.TicketDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Booking;
import com.bookmyshow.boot.bookmyshow.project.entity.Ticket;
import com.bookmyshow.boot.bookmyshow.project.exception.BookingNotDone;
import com.bookmyshow.boot.bookmyshow.project.exception.TicketNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class BookingService 
{
	@Autowired
	BookingDao bookingDao;
	@Autowired
	TicketDao ticketDao;
	
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking)
	{
		ResponseStructure<Booking> structure=new ResponseStructure<Booking>();
		structure.setMessage(" booking object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(bookingDao.saveBooking(booking));
		return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Booking>> findBooking(int bookingId)
	{
		Booking booking=bookingDao.findBooking(bookingId);
		if(booking!=null)
		{
	    ResponseStructure<Booking> structure=new ResponseStructure<Booking>();
	    structure.setMessage("Found booking");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(booking);
		return  new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.FOUND);
		}
		throw new BookingNotDone("booking not done for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(int bookingId)
	{
		Booking booking=bookingDao.findBooking(bookingId);
		if(booking!=null)
		{
			bookingDao.deleteBooking(bookingId);
			ResponseStructure<Booking> structure=new ResponseStructure<Booking>();
			structure.setMessage("booking deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(booking);
		return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
		}
		throw new BookingNotDone("booking not done for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking,int bookingId)
	{
		Booking exbooking=bookingDao.findBooking(bookingId);
		if(exbooking!=null) 
		{
		ResponseStructure<Booking> structure=new ResponseStructure<Booking>();
		structure.setMessage("booking Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(bookingDao.updateBooking(booking, bookingId));
		return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
		}
		throw new BookingNotDone("booking not done for the given id");
	}
	
	public List<Booking> findAllBookings()
	{
		List<Booking> bookingList=bookingDao.findAllBooking();
		if(bookingList!=null) 
		{
		ResponseStructure<List<Booking>> structure= new ResponseStructure<List<Booking>>();
		structure.setMessage("list of all booking");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(bookingList);
		return bookingDao.findAllBooking();
		}
		throw new BookingNotDone("booking not done for the given id");
	}

	public ResponseEntity<ResponseStructure<Booking>> setTicketToBooking(int bookingId,int ticketId)
	{
		Booking booking= bookingDao.findBooking(bookingId);
		if(booking!=null)
		{
		Ticket ticket=ticketDao.findTicket(ticketId);
		if(ticket!=null)
		{
			
				if(booking.getTicket().equals(null))
				{
					booking.setTicket(ticket);
					ResponseStructure<Booking>structure=new ResponseStructure<Booking>();
					structure.setMessage("booking is done for the ticket");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(bookingDao.updateBooking(booking, bookingId));
					return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);
					
				}
				throw new TicketNotFound("this ticket already booked");
			
		}
		throw new TicketNotFound("ticket object not found for the given id");
		}
		throw new BookingNotDone("booking is not found for the given Id");
	}

}
