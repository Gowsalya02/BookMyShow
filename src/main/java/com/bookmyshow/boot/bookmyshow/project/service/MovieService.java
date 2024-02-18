package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.MovieDao;
import com.bookmyshow.boot.bookmyshow.project.dao.ReviewDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Movie;
import com.bookmyshow.boot.bookmyshow.project.entity.Review;
import com.bookmyshow.boot.bookmyshow.project.exception.MovieNotFound;
import com.bookmyshow.boot.bookmyshow.project.exception.ReviewNotFound;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class MovieService 
{
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	ReviewDao reviewDao;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie)
	{
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		structure.setMessage(" movie object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(movieDao.saveMovie(movie));
		return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int movieId)
	{
		Movie movie=movieDao.findMovie(movieId);
		if(movie!=null)
		{
	    ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
	    structure.setMessage("Found movie");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(movie);
		return  new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("movie object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int movieId)
	{
		Movie movie=movieDao.findMovie(movieId);
		if(movie!=null)
		{
			if(movie.getReviewList()==null)
			{
			movieDao.deleteMovie(movieId);
			}
			else
			{
				movie.setReviewList(null);
				List<Review> reviewList= movie.getReviewList();
				for (Review review : reviewList) 
				{
					review.setMovie(null);
				}
			movieDao.deleteMovie(movieId);
			}
			ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
			structure.setMessage("movie deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(movie);
		return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("movie object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie,int movieId)
	{
		Movie exmovie=movieDao.findMovie(movieId);
		if(exmovie!=null) 
		{
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		structure.setMessage("movie Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(movieDao.updateMovie(movie, movieId));
		return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("movie object not found for the given id");
	}
	
	public List<Movie> findAllMovies()
	{
		List<Movie> movieList=movieDao.findAllMovies();
		if(movieList!=null) 
		{
		ResponseStructure<List<Movie>> structure= new ResponseStructure<List<Movie>>();
		structure.setMessage("list of all movie");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(movieList);
		return movieDao.findAllMovies();
		}
		return null;//no movies available
	}


	public ResponseEntity<ResponseStructure<Movie>> setReviewToMovie(int movieId,int reviewId)
	{

		Movie movie=movieDao.findMovie(movieId);
		
		if(movie!=null)
		{
			Review exReview=reviewDao.findReview(reviewId);
		  if(exReview!=null)
		   {
			List<Review>movieReview=movie.getReviewList();
			if(movieReview.isEmpty())
			{
				List<Review> newReviewList=new ArrayList<Review>();
				newReviewList.add(exReview);
				movie.setReviewList(newReviewList);
			}
			else
			{
				List<Review> reviewList=reviewDao.findAllReviews();
				for (Review review : reviewList) 
				{
					if(review.getReviewId()==reviewId)
					{
						movieReview.add(reviewDao.findReview(reviewId));
						movie.setReviewList(movieReview);
					}
				}
			}		
				ResponseStructure<Movie> structure= new ResponseStructure<Movie>();
				structure.setMessage("review given for the movie");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(movieDao.updateMovie(movie, movieId));
				return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		   }
			throw new ReviewNotFound("review not found for given id");
			}
			throw new MovieNotFound("movie not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Movie>> removeReviewFromMovie(int movieId,int reviewId)
	{
		Movie movie=movieDao.findMovie(movieId);
		if(movie!=null)
		{
			List<Review>movieReview=movie.getReviewList();
			
			for (Review review : movieReview) 
			{
				if(review.getReviewId()==reviewId)
				{
					movieReview.remove(review);
				}
			}
			
			movie.setReviewList(movieReview);
			ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
			structure.setMessage("review removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(movieDao.updateMovie(movie, movieId));
		return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("movie object not found for the given id");
	}
}
