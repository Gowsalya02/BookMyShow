package com.bookmyshow.boot.bookmyshow.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.boot.bookmyshow.project.entity.Review;
import com.bookmyshow.boot.bookmyshow.project.repository.ReviewRepository;
@Repository
public class ReviewDao 
{

	@Autowired
	ReviewRepository reviewRepository;
	
	public Review saveReview(Review review)
	{
		return reviewRepository.save(review);
	}
	
	public Review findReview(int reviewId)
	{
		Optional<Review> optional= reviewRepository.findById(reviewId);
		
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;//review object not present
	}
	
	public Review deleteReview(int reviewId)
	{
		Review exReview=findReview(reviewId);
		if(exReview!=null)
		{
		reviewRepository.deleteById(reviewId);
		return exReview;
		}
	
		return null;//review object is found for the given ID
	}

	public Review updatereview(Review review,int reviewId)
	{
		Review exReview=findReview(reviewId);
		
		if(exReview!=null)
		{
			review.setReviewId(reviewId);
			return reviewRepository.save(review);
		}
		return null;//review object is not found for the given ID
	}
	
	public List<Review> findAllReviews()
	{
		return reviewRepository.findAll();
	}
}
