package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Admin;
import com.bookmyshow.boot.bookmyshow.project.repository.AdminRepository;

@Repository
public class AdminDao 
{
	@Autowired
	AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin)
	{
		return adminRepository.save(admin);
	}
	
	public Admin findAdmin(int adminId)
	{
		Optional<Admin> optional= adminRepository.findById(adminId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//admin object not present
	}
	
	public Admin deleteAdmin(int adminId)
	{
		Admin exAdmin=findAdmin(adminId);
		if(exAdmin!=null)
		{
		adminRepository.deleteById(adminId);
		return exAdmin;
		}
	
		return null;//admin object is found for the given ID
	}

	public Admin updateAdmin(Admin admin,int adminId)
	{
		Admin exAdmin=findAdmin(adminId);
		
		if(exAdmin!=null)
		{
			admin.setAdminId(adminId);
			return adminRepository.save(admin);
		}
		return null;//admin object is not found for the given ID
	}
	
	public List<Admin> findAllAdmins()
	{
		return adminRepository.findAll();
	}
	
}
