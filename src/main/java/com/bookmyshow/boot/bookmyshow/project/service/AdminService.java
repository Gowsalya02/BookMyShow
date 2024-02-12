package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.AdminDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Admin;
import com.bookmyshow.boot.bookmyshow.project.exception.AdminNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class AdminService
{
	@Autowired
	AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin)
	{
		ResponseStructure<Admin> structure=new ResponseStructure<Admin>();
		structure.setMessage(" admin object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(adminDao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findAdmin(int adminId)
	{
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null)
		{
	    ResponseStructure<Admin> structure=new ResponseStructure<Admin>();
	    structure.setMessage("Found admin");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(admin);
		return  new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.FOUND);
		}
		throw new AdminNotFound("admin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int adminId)
	{
		Admin admin=adminDao.findAdmin(adminId);
		if(admin!=null)
		{
			adminDao.deleteAdmin(adminId);
			ResponseStructure<Admin> structure=new ResponseStructure<Admin>();
			structure.setMessage("admin deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(admin);
		return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("admin object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin,int adminId)
	{
		Admin exadmin=adminDao.findAdmin(adminId);
		if(exadmin!=null) 
		{
		ResponseStructure<Admin> structure=new ResponseStructure<Admin>();
		structure.setMessage("admin Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(adminDao.updateAdmin(admin, adminId));
		return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("admin object not found for the given id");
	}
	
	public List<Admin> findAllAdmins()
	{
		List<Admin> adminList=adminDao.findAllAdmins();
		if(adminList!=null) 
		{
		ResponseStructure<List<Admin>> structure= new ResponseStructure<List<Admin>>();
		structure.setMessage("list of all admin");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(adminList);
		return adminDao.findAllAdmins();
		}
		return null;//no admins available
	}


}
