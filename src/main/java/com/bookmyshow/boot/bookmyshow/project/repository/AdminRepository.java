package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.boot.bookmyshow.project.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>
{
	@Query("select a  from Admin a where a.adminMail=?1")
	public Admin findByMail(String adminMail);

}

