package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.User;
import com.joycehwchan.diningreviews.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        validateUser(user);
        userRepository.save(user);
    }

    private void validateUser (User user) {
        // validations...
    }
}
