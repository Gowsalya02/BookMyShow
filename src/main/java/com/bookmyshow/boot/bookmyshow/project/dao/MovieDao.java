package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Movie;
import com.bookmyshow.boot.bookmyshow.project.repository.MovieRepository;
@Repository
public class MovieDao
{
	@Autowired
	MovieRepository movieRepository;
	
	public Movie saveMovie(Movie movie)
	{
		return movieRepository.save(movie);
	}
	
	public Movie findMovie(int movieId)
	{
		Optional<Movie> optional= movieRepository.findById(movieId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//movie object not present
	}
	
	public Movie deleteMovie(int movieId)
	{
		Movie exMovie=findMovie(movieId);
		if(exMovie!=null)
		{
		movieRepository.deleteById(movieId);
		return exMovie;
		}
	
		return null;//movie object is found for the given ID
	}

	public Movie updateMovie(Movie movie,int movieId)
	{
		Movie exMovie=findMovie(movieId);
		
		if(exMovie!=null)
		{
			movie.setMovieId(movieId);
			return movieRepository.save(movie);
		}
		return null;//movie object is not found for the given ID
	}
	
	public List<Movie> findAllMovies()
	{
		return movieRepository.findAll();
	}

}
