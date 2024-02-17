package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.AdminDao;
import com.bookmyshow.boot.bookmyshow.project.dao.TheatreDao;
import com.bookmyshow.boot.bookmyshow.project.dto.AdminDto;
import com.bookmyshow.boot.bookmyshow.project.entity.Admin;
import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;
import com.bookmyshow.boot.bookmyshow.project.exception.AdminNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class AdminService
{
	@Autowired
	AdminDao adminDao;
	
	
	@Autowired
	TheatreDao theatreDao;
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin)
	{
		AdminDto adminDto=new AdminDto();
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.map(adminDao.saveAdmin(admin),adminDto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage(" admin object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId)
	{
		AdminDto adminDto=new AdminDto();
		ModelMapper modelMapper=new ModelMapper();
		
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null)
		{
			modelMapper.map(admin,adminDto);
	    ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
	    structure.setMessage("Found admin");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(adminDto);
		return  new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new AdminNotFound("admin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId)
	{
		AdminDto adminDto=new AdminDto();
		ModelMapper modelMapper=new ModelMapper();
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null)
		{
			modelMapper.map(admin,adminDto);
			adminDao.deleteAdmin(adminId);
			ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
			structure.setMessage("admin deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("admin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(Admin admin,int adminId)
	{
		
		AdminDto adminDto=new AdminDto();
		ModelMapper modelMapper=new ModelMapper();
		Admin exadmin=adminDao.findAdmin(adminId);
		if(exadmin!=null) 
		{
		modelMapper.map(adminDao.updateAdmin(admin, adminId), adminDto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("admin Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("admin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmins()
	{

		 List<Admin> adminList=adminDao.findAllAdmins();
		 List<AdminDto> adminDtoList=new ArrayList<AdminDto>();
		 if(adminList!=null) 
			{
		 for (Admin admin : adminList) 
		 {
			 AdminDto adminDto=new AdminDto();
			 ModelMapper modelMapperl=new ModelMapper();
			 modelMapperl.map(admin,adminDto);
			 adminDtoList.add(adminDto);
		 }
		
		ResponseStructure<List<AdminDto>> structure= new ResponseStructure<List<AdminDto>>();
		structure.setMessage("list of all admin");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(adminDtoList);
		return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure,HttpStatus.FOUND);
		}
		return null;//no admins available
	}


	public ResponseEntity<ResponseStructure<AdminDto>> adminLogin(String adminMail,String adminPassword)
	{
		
		AdminDto adminDto=new AdminDto();
		ModelMapper modelMapper=new ModelMapper();
		Admin admin=adminDao.findByMail(adminMail);
		
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		if(admin!=null)
		{
			if(admin.getAdminPassword().equals(adminPassword))
			{
				modelMapper.map(admin, adminDto);
			 structure.setData(adminDto);
			 structure.setMessage("Admin login succesfully");
			 structure.setStatus(HttpStatus.ACCEPTED.value());
			 
			 return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.ACCEPTED);
			} 
			throw new AdminNotFound("admin password is not matching");
			
		}
		throw new AdminNotFound("admin object not found for the given mail id");
	}
	public ResponseEntity<ResponseStructure<AdminDto>> addTheatreToAdmin(String adminMail,String adminPassword,int theatreId)
	{
		AdminDto adminDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		ResponseEntity<ResponseStructure<AdminDto>> exAdminDto=adminLogin(adminMail,adminPassword);
		
		Admin admin=adminDao.findByMail(adminMail);
		
		if(exAdminDto!=null)
		{
			    List<Theatre> theatreList=theatreDao.findAllTheatres();
			    List<Theatre>adminTheatre=admin.getTheatreList();
			    if(adminTheatre.isEmpty())
			    {
			    	List<Theatre> newTheatreList=new ArrayList<Theatre>();
			    	newTheatreList.add(theatreDao.findTheatre(theatreId));
			    	admin.setTheatreList(newTheatreList);	
			    }
			    else 
			    {
			    	for (Theatre theatre : theatreList) 
			    	{
			    		if(theatre.getTheatreId()==theatreId)
			    		{
			    			adminTheatre.add(theatreDao.findTheatre(theatreId));
			    			admin.setTheatreList(theatreList);
			    		}
						
					}
			    }
			    
			    mapper.map(adminDao.updateAdmin(admin,admin.getAdminId()), adminDto);
			    ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
			    structure.setMessage("Theatre list assign to admin");
			    structure.setStatus(HttpStatus.OK.value());
			    structure.setData(adminDto);
			    
          return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		
		throw new AdminNotFound("Admin object is not found for the given ");
		
	}
}
