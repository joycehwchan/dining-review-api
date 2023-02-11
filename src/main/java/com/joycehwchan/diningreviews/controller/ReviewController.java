package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.Review;
import com.joycehwchan.diningreviews.model.ReviewStatus;
import com.joycehwchan.diningreviews.repository.RestaurantRepository;
import com.joycehwchan.diningreviews.repository.ReviewRepository;
import com.joycehwchan.diningreviews.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewController(ReviewRepository reviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addReview(@RequestBody Review review) {
        validateReview(review);

        review.setStatus(ReviewStatus.PENDING);
        reviewRepository.save(review);
    }

    @GetMapping
    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    private void validateReview(Review review) {
        // validations...
    }
}
