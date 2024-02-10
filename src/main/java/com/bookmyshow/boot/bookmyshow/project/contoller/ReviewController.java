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

import com.bookmyshow.boot.bookmyshow.project.entity.Review;
import com.bookmyshow.boot.bookmyshow.project.service.ReviewService;
import com.bookmyshow.boot.bookmyshow.project.util.ResponseStructure;

@RequestMapping("review")
@RestController
public class ReviewController 
{
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Review>> saveReview(@RequestBody  Review review) 
	{
		return reviewService.saveReview(review);	
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Review>> findReview(@RequestParam int reviewId) 
	{
		return reviewService.findReview(reviewId);	
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Review>> deleteReview(@RequestParam int reviewId) 
	{
		return reviewService.deleteReview(reviewId);	
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Review>> updateReview(@RequestBody Review review,@RequestParam int reviewId) 
	{
		return reviewService.updateReview(review,reviewId);	
	}
	
	@GetMapping("all")
	public List<Review> findAllReview()
	{
		return reviewService.findAllReviews();
	}

}
