package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.TheatreAdminDao;
import com.bookmyshow.boot.bookmyshow.project.dao.TheatreDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;
import com.bookmyshow.boot.bookmyshow.project.entity.TheatreAdmin;
import com.bookmyshow.boot.bookmyshow.project.exception.TheatreAdminNotFound;
import com.bookmyshow.boot.bookmyshow.project.exception.TheatreNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class TheatreService 
{
	@Autowired
	TheatreDao theatreDao;
	
	@Autowired
	TheatreAdminDao theatreAdminDao;
	@Autowired
	TheatreAdminService theatreAdminService;
	
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre,String theatreAdminMail,String theatreAdminPassword)
	{
		if(theatreAdminService.theatreAdminLogin(theatreAdminMail, theatreAdminPassword).getBody().getData()!=null)
		{
	    theatre.setTheatreAdmin(theatreAdminDao.findByMail(theatreAdminMail));
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage(" theatre object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(theatreDao.saveTheatre(theatre));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.CREATED);
		}
		throw new TheatreAdminNotFound("theatre admin is not foun for the given mail id and password");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int theatreId,String theatreAdminMail,String theatreAdminPassword)
	{
		if(theatreAdminService.theatreAdminLogin(theatreAdminMail, theatreAdminPassword).getBody().getData()!=null)
		{
		Theatre theatre=theatreDao.findTheatre(theatreId);
		if(theatre!=null)
		{
			if(theatreId==(theatreAdminDao.findByMail(theatreAdminMail).getTheatre().getTheatreId()))
			{
	    ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
	    structure.setMessage("Found theatre");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(theatre);
		return  new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.FOUND);
			}
			throw new TheatreNotFound("theatre not found for the given admin");
		}
		throw new TheatreNotFound("theatre object not found for the given id");
		}
		throw new TheatreAdminNotFound("theatre admin is not foun for the given mail id and password");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int theatreId,String theatreAdminMail,String theatreAdminPassword)
	{
		if(theatreAdminService.theatreAdminLogin(theatreAdminMail, theatreAdminPassword).getBody().getData()!=null)
		{
			TheatreAdmin theatreAdmin=theatreAdminDao.findByMail(theatreAdminMail);
		Theatre theatre =theatreDao.findTheatre(theatreId);
		if(theatre!=null)
		{
		
			theatre.setTheatreAdmin(null);
			theatreAdmin.setTheatre(null);
			theatreDao.deleteTheatre(theatreId);
			ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
			structure.setMessage("theatre deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatre);
		    return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		
		}
		throw new TheatreNotFound("theatre object not found for the given id");
		}
		throw new TheatreAdminNotFound("theatre admin is not foun for the given mail id and password");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre,int theatreId,String theatreAdminMail,String theatreAdminPassword)
	{
		if(theatreAdminService.theatreAdminLogin(theatreAdminMail, theatreAdminPassword).getBody().getData()!=null)
		{
		Theatre extheatre=theatreDao.findTheatre(theatreId);
		if(extheatre!=null) 
		{
		
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage("theatre Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(theatreDao.updateTheatre(theatre, theatreId));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("theatre object not found for the given id");
		}
		throw new TheatreAdminNotFound("theatre admin is not foun for the given mail id and password");
	}
	
	public List<Theatre> findAllTheatres()
	{
		List<Theatre> theatreList=theatreDao.findAllTheatres();
		if(theatreList!=null) 
		{
		ResponseStructure<List<Theatre>> structure= new ResponseStructure<List<Theatre>>();
		structure.setMessage("list of all theatre");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(theatreList);
		return theatreDao.findAllTheatres();
		}
		return null;//no theatres available
	}

	public ResponseEntity<ResponseStructure<Theatre>> setAdminToTheatre(int theatreId,String theatreAdminMail,String theatreAdminPassword)
	{
		if(theatreAdminService.theatreAdminLogin(theatreAdminMail, theatreAdminPassword).getBody().getData()!=null)
		{
			TheatreAdmin theatreAdmin=theatreAdminDao.findByMail(theatreAdminMail);
		Theatre extheatre=theatreDao.findTheatre(theatreId);
		if(extheatre!=null) 
		{
			extheatre.setTheatreAdmin(theatreAdmin);
			theatreAdmin.setTheatre(extheatre);
			ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
			structure.setMessage("theatre Updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatreDao.updateTheatre(extheatre, theatreId));
			return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
			
	     throw new TheatreNotFound("theatre object not found for the given id");
		}
		
		throw new TheatreAdminNotFound("theatre admin is not foun for the given mail id and password");

		
	}

}
