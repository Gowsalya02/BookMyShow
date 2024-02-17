package com.bookmyshow.boot.bookmyshow.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.boot.bookmyshow.project.entity.TheatreAdmin;

public interface TheatreAdminRepository extends JpaRepository<TheatreAdmin, Integer>
{

//	@Query("select t  from TheaterAdmin t where t.theatreAdminMail=?1")
//	public TheatreAdmin findByAdminMail(String theatreAdminMail);
}
