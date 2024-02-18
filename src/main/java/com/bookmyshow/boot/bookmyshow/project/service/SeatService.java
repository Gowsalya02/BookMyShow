package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.SeatDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Seat;
import com.bookmyshow.boot.bookmyshow.project.entity.SeatType;
import com.bookmyshow.boot.bookmyshow.project.exception.InvalidType;
import com.bookmyshow.boot.bookmyshow.project.exception.SeatNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class SeatService 
{@Autowired
	SeatDao seatDao;
	
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(Seat seat)
	{
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		structure.setMessage(" seat object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(seatDao.saveSeat(seat));
		return new ResponseEntity<ResponseStructure<Seat>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Seat>> findSeat(int seatId)
	{
		Seat seat=seatDao.findSeat(seatId);
		if(seat!=null)
		{
	    ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
	    structure.setMessage("Found seat");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(seat);
		return  new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.FOUND);
		}
		throw new SeatNotFound("seat object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Seat>> deleteSeat(int seatId)
	{
		Seat seat =seatDao.findSeat(seatId);
		if(seat!=null)
		{
			seatDao.deleteSeat(seatId);
			ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
			structure.setMessage("seat deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(seat);
		return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.OK);
		}
		throw new SeatNotFound("seat object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Seat>> updateSeat(Seat seat,int seatId)
	{
		Seat exseat=seatDao.findSeat(seatId);
		if(exseat!=null) 
		{
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		structure.setMessage("seat Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(seatDao.updateSeat(seat, seatId));
		return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.OK);
		}
		throw new SeatNotFound("seat object not found for the given id");
	}
	
	public List<Seat> findAllSeats()
	{
		List<Seat> seatList=seatDao.findAllSeats();
		if(seatList!=null) 
		{
		ResponseStructure<List<Seat>> structure= new ResponseStructure<List<Seat>>();
		structure.setMessage("list of all seat");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(seatList);
		return seatDao.findAllSeats();
		}
		return null;//no seats available
	}
	public ResponseEntity<ResponseStructure<Seat>> setSeatType(int seatId,int seatType)
	{
		Seat seat= seatDao.findSeat(seatId);
		if(seat!=null)
		{
		switch (seatType) 
		{
		case  1:seat.setSeatType(SeatType.firstclass);break;
		case  2:seat.setSeatType(SeatType.secondclass);;break;
		case  3:seat.setSeatType(SeatType.thirdclass);break;
		default:throw new InvalidType("invalid  status type");
		}
		
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		structure.setMessage("seat Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(seatDao.updateSeat(seat, seatId));
		return new ResponseEntity<ResponseStructure<Seat>>(structure,HttpStatus.OK);
		
		}
		throw new SeatNotFound("seat is not found for the given id");
	}
	

}
