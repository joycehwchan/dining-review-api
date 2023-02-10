package com.joycehwchan.diningreviews.repository;

import com.joycehwchan.diningreviews.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
