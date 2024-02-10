package com.bookmyshow.boot.bookmyshow.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.TheatreAdmin;

public interface TheatreAdminRepository extends JpaRepository<TheatreAdmin, Integer>
{

}
