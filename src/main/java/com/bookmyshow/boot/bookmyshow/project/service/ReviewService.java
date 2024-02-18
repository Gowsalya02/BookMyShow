package com.bookmyshow.boot.bookmyshow.project.service;

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
public class ReviewService 
{
	@Autowired
	ReviewDao reviewDao;
	@Autowired
	MovieDao movieDao;
	public ResponseEntity<ResponseStructure<Review>> saveReview(Review review)
	{
		ResponseStructure<Review> structure=new ResponseStructure<Review>();
		structure.setMessage(" review object saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(reviewDao.saveReview(review));
		return new ResponseEntity<ResponseStructure<Review>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Review>> findReview(int reviewId)
	{
		Review review=reviewDao.findReview(reviewId);
		if(review!=null)
		{
	    ResponseStructure<Review> structure=new ResponseStructure<Review>();
	    structure.setMessage("Found review");
	    structure.setStatus(HttpStatus.FOUND.value());
	    structure.setData(review);
		return  new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.FOUND);
		}
		throw new ReviewNotFound("review object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Review>> deleteReview(int reviewId)
	{
		Review review=reviewDao.findReview(reviewId);
		if(review!=null)
		{
			if(review.getMovie()==null)
			{
			reviewDao.deleteReview(reviewId);
			}
			else
			{
				review.getMovie().setReviewList(null);
				review.setMovie(null);
				reviewDao.deleteReview(reviewId);
			}
			ResponseStructure<Review> structure=new ResponseStructure<Review>();
			structure.setMessage("review deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(review);
		return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
		}
		throw new ReviewNotFound("review object not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<Review>> updateReview(Review review,int reviewId)
	{
		Review exreview=reviewDao.findReview(reviewId);
		if(exreview!=null) 
		{
		ResponseStructure<Review> structure=new ResponseStructure<Review>();
		structure.setMessage("review Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(reviewDao.updatereview(review, reviewId));
		return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
		}
		throw new ReviewNotFound("review object not found for the given id");
	}
	
	public List<Review> findAllReviews()
	{
		List<Review> reviewList=reviewDao.findAllReviews();
		if(reviewList!=null) 
		{
		ResponseStructure<List<Review>> structure= new ResponseStructure<List<Review>>();
		structure.setMessage("list of all review");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(reviewList);
		return reviewDao.findAllReviews();
		}
		return null;//no reviews available
	}

	public ResponseEntity<ResponseStructure<Review>> setMovieToReview(int reviewId,int movieId)
	{
		Review review= reviewDao.findReview(reviewId);
		
		if(review!=null)
		{
			Movie movie=movieDao.findMovie(movieId);
			
			if(movie!=null) 
			{
				review.setMovie(movie);
				reviewDao.updatereview(review, reviewId);
				
				ResponseStructure<Review> structure= new ResponseStructure<Review>();
				structure.setMessage("review given for the movie");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(reviewDao.updatereview(review, reviewId));
				return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
			}
			throw new MovieNotFound("movie not found for the given id");
		}
		throw new ReviewNotFound("review not found for given id");
	}

	public ResponseEntity<ResponseStructure<Review>> removeMovieFromReview(int reviewId,int movieId)
	{
		Review review=reviewDao.findReview(reviewId);
		if(review!=null)
		{
			if(review.getMovie().equals(movieDao.findMovie(movieId)))
			{
				review.getMovie().setReviewList(null);
				review.setMovie(null);
				movieDao.updateMovie(review.getMovie(), movieId);
				ResponseStructure<Review> structure=new ResponseStructure<Review>();
				structure.setMessage("movie removed from review");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(reviewDao.updatereview(review, reviewId));
			return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
			}
			throw new MovieNotFound("movie is not for the given review");
		}
		throw new ReviewNotFound("review object not found for the given id");
	}
}
