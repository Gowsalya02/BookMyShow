package com.bookmyshow.boot.bookmyshow.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.boot.bookmyshow.project.dao.ReviewDao;
import com.bookmyshow.boot.bookmyshow.project.entity.Review;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@Service
public class ReviewService 
{
	@Autowired
	ReviewDao reviewDao;
	
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
		return null;//review object not found for the given id
	}
	
	public ResponseEntity<ResponseStructure<Review>> deleteReview(int reviewId)
	{
		Review review=reviewDao.findReview(reviewId);
		if(review!=null)
		{
			reviewDao.deleteReview(reviewId);
			ResponseStructure<Review> structure=new ResponseStructure<Review>();
			structure.setMessage("review deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(review);
		return new ResponseEntity<ResponseStructure<Review>>(structure,HttpStatus.OK);
		}
		return null;//review object not found for the given id
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
		return null;//review object not found for the given id
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


}
