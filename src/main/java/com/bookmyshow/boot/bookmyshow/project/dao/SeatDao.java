package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Seat;
import com.bookmyshow.boot.bookmyshow.project.repository.SeatRepository;

@Repository
public class SeatDao 
{
	@Autowired
	SeatRepository seatRepository;
	
	public Seat saveSeat(Seat seat)
	{
		return seatRepository.save(seat);
	}
	
	public Seat findSeat(int seatId)
	{
		Optional<Seat> optional= seatRepository.findById(seatId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//seat object not present
	}
	
	public Seat deleteSeat(int seatId)
	{
		Seat exSeat=findSeat(seatId);
		if(exSeat!=null)
		{
			seatRepository.deleteById(seatId);
		return exSeat;
		}
	
		return null;//seat object is found for the given ID
	}

	public Seat updateSeat(Seat seat,int seatId)
	{
		Seat exSeat=findSeat(seatId);
		
		if(exSeat!=null)
		{
			seat.setSeatId(seatId);
			return seatRepository.save(seat);
		}
		return null;//seat object is not found for the given ID
	}
	
	public List<Seat> findAllSeats()
	{
		return seatRepository.findAll();
	}


}
