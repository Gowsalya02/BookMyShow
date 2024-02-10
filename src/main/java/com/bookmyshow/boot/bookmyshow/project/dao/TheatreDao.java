package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;
import com.bookmyshow.boot.bookmyshow.project.repository.TheatreRepository;

@Repository
public class TheatreDao 
{
	@Autowired
	TheatreRepository theatreRepository;
	
	public Theatre saveTheatre(Theatre theatre)
	{
		return theatreRepository.save(theatre);
	}
	
	public Theatre findTheatre(int theatreId)
	{
		Optional<Theatre> optional= theatreRepository.findById(theatreId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//theatre object not present
	}
	
	public Theatre deleteTheatre(int theatreId)
	{
		Theatre exTheatre=findTheatre(theatreId);
		if(exTheatre!=null)
		{
			theatreRepository.deleteById(theatreId);
		return exTheatre;
		}
	
		return null;//theatre object is found for the given ID
	}

	public Theatre updateTheatre(Theatre theatre,int theatreId)
	{
		Theatre exTheatre=findTheatre(theatreId);
		
		if(exTheatre!=null)
		{
			theatre.setTheatreId(theatreId);
			return theatreRepository.save(theatre);
		}
		return null;//theatre object is not found for the given ID
	}
	
	public List<Theatre> findAllTheatres()
	{
		return theatreRepository.findAll();
	}

}
