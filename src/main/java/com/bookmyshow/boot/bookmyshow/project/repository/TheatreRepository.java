package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.Theatre;

public interface TheatreRepository  extends JpaRepository<Theatre, Integer>
{

}
