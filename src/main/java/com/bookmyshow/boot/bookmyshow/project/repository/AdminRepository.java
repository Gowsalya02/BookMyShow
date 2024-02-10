package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
