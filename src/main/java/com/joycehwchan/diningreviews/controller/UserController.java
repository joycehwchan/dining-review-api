package com.joycehwchan.diningreviews.controller;

import com.joycehwchan.diningreviews.model.User;
import com.joycehwchan.diningreviews.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        validateUsername(username);

        Optional<User> optionalExistingUser = userRepository.findByUsername(username);
        if (!optionalExistingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalExistingUser.get();
        existingUser.setId(null);

        return existingUser;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        validateUser(user);
        userRepository.save(user);
    }

    private void validateUser (User user) {
        validateUsername(user.getUsername());

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateUsername (String username) {
        if (ObjectUtils.isEmpty(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

