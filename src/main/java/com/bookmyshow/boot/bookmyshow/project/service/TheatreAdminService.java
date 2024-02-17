package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.TheatreAdminDao;
import com.bookmyshow.boot.bookmyshow.project.dto.TheatreAdminDto;
import com.bookmyshow.boot.bookmyshow.project.entity.TheatreAdmin;
import com.bookmyshow.boot.bookmyshow.project.exception.TheatreAdminNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class TheatreAdminService 
{

	@Autowired
	TheatreAdminDao theatreAdminDao;
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(TheatreAdmin theatreAdmin)
	{
		TheatreAdminDto theatreAdminDto=new TheatreAdminDto();
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.map(theatreAdminDao.saveTheatreAdmin(theatreAdmin),theatreAdminDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage(" TheatreAdmin object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(theatreAdminDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findTheatreAdmin(int theatreAdminId)
	{
		TheatreAdminDto theatreAdminDto=new TheatreAdminDto();
		ModelMapper modelMapper=new ModelMapper();
		TheatreAdmin theatreAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdmin!=null)
		{
	    modelMapper.map(theatreAdmin,theatreAdminDto);
	    ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
	    structure.setMessage("Found TheatreAdmin");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(theatreAdminDto);
		return  new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("theatreAdmin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(int theatreAdminId)
	{
		TheatreAdminDto theatreAdminDto=new TheatreAdminDto();
		ModelMapper modelMapper=new ModelMapper();
		TheatreAdmin theatreAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(theatreAdmin!=null)
		{
			 modelMapper.map(theatreAdmin,theatreAdminDto);
			theatreAdminDao.deleteTheatreAdmin(theatreAdminId);
			ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
			structure.setMessage("theatreAdmin deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatreAdminDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("theatreAdmin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateTheatreAdmin(TheatreAdmin theatreAdmin,int theatreAdminId)
	{
		TheatreAdminDto theatreAdminDto=new TheatreAdminDto();
		ModelMapper modelMapper=new ModelMapper();
		TheatreAdmin exTheatreAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(exTheatreAdmin!=null) 
		{
	    modelMapper.map(theatreAdminDao.updateTheatreAdmin(theatreAdmin, theatreAdminId),theatreAdminDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("theatreAdmin Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(theatreAdminDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("theatreAdmin object not found for the given id");
	}
	
	public List<TheatreAdminDto> findAllTheatreAdmins()
	{
		List<TheatreAdmin> theatreAdminList=theatreAdminDao.findAllTheatreAdmins();
		 List<TheatreAdminDto> theatreAdminDtoList=new ArrayList<TheatreAdminDto>();
		 if(theatreAdminList!=null) 
			{
		 for (TheatreAdmin theatreAdmin : theatreAdminList) 
		 {
			 TheatreAdminDto theatreAdminDto=new TheatreAdminDto();
			 ModelMapper modelMapperl=new ModelMapper();
			 modelMapperl.map(theatreAdmin,theatreAdminDto);
			 theatreAdminDtoList.add(theatreAdminDto);
		 }
			
		ResponseStructure<List<TheatreAdminDto>> structure= new ResponseStructure<List<TheatreAdminDto>>();
		structure.setMessage("list of all theatreAdmin");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(theatreAdminDtoList);
		return theatreAdminDtoList;
		}
		return null;//no theatreAdmins available
	}

//	public ResponseEntity<ResponseStructure<TheatreAdminDto>> theatreAdminLogin(String theatreAdminMail,String theatreAdminPassword)
//	{
//		
//		TheatreAdminDto theatreAdminDto=new TheatreAdminDto();
//		ModelMapper modelMapper=new ModelMapper();
//		TheatreAdmin theatreAdmin=theatreAdminDao.findByMail(theatreAdminMail);
//		
//		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
//		if(theatreAdmin!=null)
//		{
//			if(theatreAdmin.getTheatreAdminPassword().equals(theatreAdminPassword))
//			{
//				modelMapper.map(theatreAdmin, theatreAdminDto);
//			 structure.setData(theatreAdminDto);
//			 structure.setMessage("theatreAdmin login succesfully");
//			 structure.setStatus(HttpStatus.ACCEPTED.value());
//			 
//			 return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.ACCEPTED);
//			} 
//			throw new TheatreAdminNotFound("theatreAdmin password is not matching");
//			
//		}
//		throw new TheatreAdminNotFound("theatreAdmin object not found for the given mail id");
//	}

}
