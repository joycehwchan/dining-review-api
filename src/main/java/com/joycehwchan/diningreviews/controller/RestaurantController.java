package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.Restaurant;
import com.joycehwchan.diningreviews.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


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

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        // add validations...
        return restaurantRepository.save(restaurant);
    }
}
