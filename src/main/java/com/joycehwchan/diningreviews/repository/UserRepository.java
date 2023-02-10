package com.joycehwchan.diningreviews.repository;

import com.joycehwchan.diningreviews.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
