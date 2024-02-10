package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> 
{

}
