package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
