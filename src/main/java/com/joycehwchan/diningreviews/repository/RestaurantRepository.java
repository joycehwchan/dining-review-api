package com.joycehwchan.diningreviews.repository;

import com.joycehwchan.diningreviews.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
