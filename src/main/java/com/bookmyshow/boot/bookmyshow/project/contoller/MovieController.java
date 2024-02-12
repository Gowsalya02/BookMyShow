package com.bookmyshow.boot.bookmyshow.project.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.boot.bookmyshow.project.entity.Movie;
import com.bookmyshow.boot.bookmyshow.project.service.MovieService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@RequestMapping("movie")
@RestController
public class MovieController 
{
	@Autowired
	MovieService movieService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestBody Movie movie) 
	{
		return movieService.saveMovie(movie);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam int movieId) 
	{
		return movieService.findMovie(movieId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId) 
	{
		return movieService.deleteMovie(movieId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestBody  Movie movie,@RequestParam int movieId) 
	{
		return movieService.updateMovie(movie,movieId);	
	}
	
	@GetMapping("all")
	public List<Movie> findAllmovie()
	{
		return movieService.findAllMovies();
	}

}
