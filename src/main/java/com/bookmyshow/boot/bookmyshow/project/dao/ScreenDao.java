package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Screen;
import com.bookmyshow.boot.bookmyshow.project.repository.ScreenRepository;

@Repository
public class ScreenDao 
{
	@Autowired
	ScreenRepository screenRepository;
	
	public Screen saveScreen(Screen screen)
	{
		return screenRepository.save(screen);
	}
	
	public Screen findScreen(int screenId)
	{
		Optional<Screen> optional= screenRepository.findById(screenId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//Screen object not present
	}
	
	public Screen deleteScreen(int screenId)
	{
		Screen exScreen=findScreen(screenId);
		if(exScreen!=null)
		{
			screenRepository.deleteById(screenId);
		return exScreen;
		}
	
		return null;//Screen object is found for the given ID
	}

	public Screen updateScreen(Screen screen,int screenId)
	{
		Screen exScreen=findScreen(screenId);
		
		if(exScreen!=null)
		{
			screen.setScreenId(screenId);
			return screenRepository.save(screen);
		}
		return null;//Screen object is not found for the given ID
	}
	
	public List<Screen> findAllScreens()
	{
		return screenRepository.findAll();
	}

}
