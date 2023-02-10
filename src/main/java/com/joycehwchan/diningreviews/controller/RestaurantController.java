package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.Restaurant;
import com.joycehwchan.diningreviews.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        // add validations...
        return restaurantRepository.save(restaurant);
    }
}
