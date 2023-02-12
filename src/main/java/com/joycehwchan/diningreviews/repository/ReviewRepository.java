package com.joycehwchan.diningreviews.repository;

import com.joycehwchan.diningreviews.model.Review;
import com.joycehwchan.diningreviews.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findReviewsByStatus(ReviewStatus reviewStatus);
}
