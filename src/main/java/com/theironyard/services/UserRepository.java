package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alhanger on 11/12/15.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByUsername(String name);
    User findOneById(Integer id);
}
