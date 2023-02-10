package com.joycehwchan.diningreviews.repository;

import com.joycehwchan.diningreviews.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
