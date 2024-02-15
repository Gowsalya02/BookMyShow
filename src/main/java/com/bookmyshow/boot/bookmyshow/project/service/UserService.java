package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.UserDao;
import com.bookmyshow.boot.bookmyshow.project.dto.UserDto;
import com.bookmyshow.boot.bookmyshow.project.entity.User;
import com.bookmyshow.boot.bookmyshow.project.exception.UserNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;


@Service
public class UserService 
{
	
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user)
	{
		UserDto userDto=new UserDto();
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.map(userDao.saveUser(user),userDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage(" User object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(userDto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int userId)
	{
		UserDto userDto=new UserDto();
		ModelMapper modelMapper=new ModelMapper();
		User user=userDao.findUser(userId);
		if(user!=null)
		{
	    modelMapper.map(user,userDto);
	    ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
	    structure.setMessage("Found User");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(userDto);
		return  new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFound("user object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int userId)
	{
		UserDto userDto=new UserDto();
		ModelMapper modelMapper=new ModelMapper();
		User user=userDao.findUser(userId);
		if(user!=null)
		{
			 modelMapper.map(user,userDto);
			userDao.deleteUser(userId);
			ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
			structure.setMessage("user deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(userDto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("user object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(User user,int userId)
	{
		UserDto userDto=new UserDto();
		ModelMapper modelMapper=new ModelMapper();
		User exUser=userDao.findUser(userId);
		if(exUser!=null) 
		{
	    modelMapper.map(userDao.updateUser(user, userId),userDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("user Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(userDto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("user object not found for the given id");
	}
	
	public List<UserDto> findAllUsers()
	{
		List<User> userList=userDao.findAllUsers();
		 List<UserDto> userDtoList=new ArrayList<UserDto>();
		 if(userList!=null) 
			{
		 for (User user : userList) 
		 {
			 UserDto userDto=new UserDto();
			 ModelMapper modelMapperl=new ModelMapper();
			 modelMapperl.map(user,userDto);
			 userDtoList.add(userDto);
		 }
			
		ResponseStructure<List<UserDto>> structure= new ResponseStructure<List<UserDto>>();
		structure.setMessage("list of all user");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(userDtoList);
		return userDtoList;
		}
		return null;//no users available
	}

	public ResponseEntity<ResponseStructure<UserDto>> userLogin(String userMail,String userPassword)
	{
		
		UserDto userDto=new UserDto();
		ModelMapper modelMapper=new ModelMapper();
		User user=userDao.findByMail(userMail);
		
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		if(user!=null)
		{
			if(user.getUserPassword().equals(userPassword))
			{
				modelMapper.map(user, userDto);
			 structure.setData(userDto);
			 structure.setMessage("user login succesfully");
			 structure.setStatus(HttpStatus.ACCEPTED.value());
			 
			 return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.ACCEPTED);
			} 
			throw new UserNotFound("user password is not matching");
			
		}
		throw new UserNotFound("user object not found for the given mail id");
	}
	
}
