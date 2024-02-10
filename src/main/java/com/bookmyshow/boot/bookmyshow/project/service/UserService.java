package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.UserDao;
import com.bookmyshow.boot.bookmyshow.project.entity.User;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;


@Service
public class UserService 
{
	
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user)
	{
		ResponseStructure<User> structure=new ResponseStructure<User>();
		structure.setMessage(" User object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(userDao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> findUser(int userId)
	{
		User user=userDao.findUser(userId);
		if(user!=null)
		{
	    ResponseStructure<User> structure=new ResponseStructure<User>();
	    structure.setMessage("Found User");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(user);
		return  new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.FOUND);
		}
		return null;//user object not found for the given id
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId)
	{
		User user=userDao.findUser(userId);
		if(user!=null)
		{
			userDao.deleteUser(userId);
			ResponseStructure<User> structure=new ResponseStructure<User>();
			structure.setMessage("user deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(user);
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		return null;//user object not found for the given id
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User user,int userId)
	{
		User exUser=userDao.findUser(userId);
		if(exUser!=null) 
		{
		ResponseStructure<User> structure=new ResponseStructure<User>();
		structure.setMessage("user Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(userDao.updateUser(user, userId));
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		return null;//user object not found for the given id
	}
	
	public List<User> findAllUsers()
	{
		List<User> userList=userDao.findAllUsers();
		if(userList!=null) 
		{
		ResponseStructure<List<User>> structure= new ResponseStructure<List<User>>();
		structure.setMessage("list of all user");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(userList);
		return userDao.findAllUsers();
		}
		return null;//no users available
	}

}
