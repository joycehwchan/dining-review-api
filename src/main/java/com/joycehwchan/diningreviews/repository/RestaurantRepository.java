package com.joycehwchan.diningreviews.repository;

import com.joycehwchan.diningreviews.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
    Iterable<Restaurant> findByZipCode(String zipCode);
    Iterable<Restaurant> findByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipCode);
    Iterable<Restaurant> findByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipCode);
    Iterable<Restaurant> findByZipCodeAndEggScoreNotNullOrderByEggScore(String zipCode);
}
