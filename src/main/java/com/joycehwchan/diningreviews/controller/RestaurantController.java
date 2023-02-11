package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.Restaurant;
import com.joycehwchan.diningreviews.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final Pattern zipCodePattern = Pattern.compile("\\d{5}");

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
        validate(restaurant);
        return restaurantRepository.save(restaurant);
    }

    private void validate(Restaurant restaurant) {
        if (ObjectUtils.isEmpty(restaurant.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        validateZipCode(restaurant.getZipCode());

        Optional<Restaurant> existingRestaurant = restaurantRepository.findByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
        if (existingRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateZipCode(String zipCode) {
        if (!zipCodePattern.matcher(zipCode).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
