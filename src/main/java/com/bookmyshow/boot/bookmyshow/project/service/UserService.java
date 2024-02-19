package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.BookingDao;
import com.bookmyshow.boot.bookmyshow.project.dao.UserDao;
import com.bookmyshow.boot.bookmyshow.project.dto.UserDto;
import com.bookmyshow.boot.bookmyshow.project.entity.Booking;
import com.bookmyshow.boot.bookmyshow.project.entity.User;
import com.bookmyshow.boot.bookmyshow.project.exception.BookingNotDone;
import com.bookmyshow.boot.bookmyshow.project.exception.UserNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;


@Service
public class UserService 
{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BookingDao bookingDao;
	
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
	
	public ResponseEntity<ResponseStructure<UserDto>> setBookingToUser(String userMail,String userPassword,int bookingId)
	{
		
	UserDto userDto=new UserDto();
	
	ModelMapper mapper=new ModelMapper();
	ResponseEntity<ResponseStructure<UserDto>> userLogin= userLogin(userMail, userPassword);
	if(userLogin.getBody().getData()!=null)
	{
		User exUser=userDao.findByMail(userMail);
		Booking booking=bookingDao.findBooking(bookingId);
		if(booking!=null)
		{
			List<Booking>userBooking= exUser.getBookingList();
			if(userBooking!=null)
			{
				List<Booking>bookingList= bookingDao.findAllBooking();
				for (Booking booking2 : bookingList) 
				{
					if(booking2.getBookingId()==bookingId)
					{
						userBooking.add(booking2);
					}
					
				}
				exUser.setBookingList(userBooking);
				
			}
			else 
			{
				List<Booking>newUserBooking=new ArrayList<Booking>();
				newUserBooking.add(bookingDao.findBooking(bookingId));
				exUser.setBookingList(newUserBooking);
			}
			
			mapper.map(userDao.updateUser(exUser, exUser.getUserId()),userDto);
			ResponseStructure<UserDto>structure=new ResponseStructure<UserDto>();
			structure.setMessage("Booking set to the user");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(userDto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.OK);
			
		}
		throw new BookingNotDone("Booking is not done for the given Id");
	}
	throw new UserNotFound("User not found for the given id");	
	}
	
}
