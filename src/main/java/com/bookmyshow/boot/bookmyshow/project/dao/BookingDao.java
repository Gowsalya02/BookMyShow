package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bookmyshow.boot.bookmyshow.project.entity.Booking;
import com.bookmyshow.boot.bookmyshow.project.repository.BookingRepository;

@Repository
public class BookingDao 
{

	@Autowired
	BookingRepository bookingRepository;
	
	public Booking saveBooking(Booking booking)
	{
		return bookingRepository.save(booking);
	}
	
	public Booking findBooking(int bookingId)
	{
		Optional<Booking> optional= bookingRepository.findById(bookingId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//booking object not present
	}
	
	public Booking deleteBooking(int bookingId)
	{
		Booking exBooking=findBooking(bookingId);
		if(exBooking!=null)
		{
			bookingRepository.deleteById(bookingId);
		return exBooking;
		}
	
		return null;//booking object is not found for the given ID
	}

	public Booking updateBooking(Booking booking,int bookingId)
	{
		Booking exBooking=findBooking(bookingId);
		
		if(exBooking!=null)
		{
			booking.setBookingId(bookingId);
			return bookingRepository.save(booking);
		}
		return null;//booking object is  not found for the given ID
	}
	
	public List<Booking> findAllBooking()
	{
		return bookingRepository.findAll();
	}
}
