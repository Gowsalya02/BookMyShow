package com.bookmyshow.boot.bookmyshow.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.boot.bookmyshow.project.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
