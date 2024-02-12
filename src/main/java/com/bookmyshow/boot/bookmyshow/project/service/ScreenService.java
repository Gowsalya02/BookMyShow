package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.ScreenDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Screen;
import com.bookmyshow.boot.bookmyshow.project.exception.ScreenNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class ScreenService 
{
	@Autowired
	ScreenDao screenDao;
	
	public ResponseEntity<ResponseStructure<Screen>> saveScreen(Screen screen)
	{
		ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
		structure.setMessage(" screen object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(screenDao.saveScreen(screen));
		return new ResponseEntity<ResponseStructure<Screen>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Screen>> findScreen(int screenId)
	{
		Screen screen=screenDao.findScreen(screenId);
		if(screen!=null)
		{
	    ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
	    structure.setMessage("Found screen");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(screen);
		return  new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.FOUND);
		}
		throw new ScreenNotFound("screen object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Screen>> deleteScreen(int screenId)
	{
		Screen screen=screenDao.findScreen(screenId);
		if(screen!=null)
		{
			screenDao.deleteScreen(screenId);
			ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
			structure.setMessage("screen deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(screen);
		return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("screen object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Screen>> updateScreen(Screen screen,int screenId)
	{
		Screen exscreen=screenDao.findScreen(screenId);
		if(exscreen!=null) 
		{
		ResponseStructure<Screen> structure=new ResponseStructure<Screen>();
		structure.setMessage("screen Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(screenDao.updateScreen(screen, screenId));
		return new ResponseEntity<ResponseStructure<Screen>>(structure,HttpStatus.OK);
		}
		throw new ScreenNotFound("screen object not found for the given id");
	}
	
	public List<Screen> findAllScreens()
	{
		List<Screen> screenList=screenDao.findAllScreens();
		if(screenList!=null) 
		{
		ResponseStructure<List<Screen>> structure= new ResponseStructure<List<Screen>>();
		structure.setMessage("list of all screen");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(screenList);
		return screenDao.findAllScreens();
		}
		return null;//no screens available
	}


}
