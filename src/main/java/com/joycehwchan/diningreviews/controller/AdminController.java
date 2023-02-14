package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.AdminReviewAction;
import com.joycehwchan.diningreviews.model.Restaurant;
import com.joycehwchan.diningreviews.model.Review;
import com.joycehwchan.diningreviews.model.ReviewStatus;
import com.joycehwchan.diningreviews.repository.RestaurantRepository;
import com.joycehwchan.diningreviews.repository.ReviewRepository;
import com.joycehwchan.diningreviews.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(ReviewRepository reviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public List<Review> getReviewsByStatus(@RequestParam String review_status) {
        ReviewStatus reviewStatus = ReviewStatus.PENDING;
        try {
            reviewStatus = ReviewStatus.valueOf((review_status.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return reviewRepository.findByStatus(reviewStatus);
    }

    @PutMapping("/reviews/{reviewId")
    public void performReviewAction(@PathVariable Long reviewId, @RequestBody AdminReviewAction adminReviewAction) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Review review = optionalReview.get();

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (adminReviewAction.getAccept()) {
            review.setStatus(ReviewStatus.APPROVED);
        } else {
            review.setStatus(ReviewStatus.REJECTED);
        }

        reviewRepository.save(review);
    }
}
