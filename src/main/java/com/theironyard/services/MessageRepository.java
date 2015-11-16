package com.theironyard.services;

import com.theironyard.entities.Message;
import com.theironyard.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by alhanger on 11/12/15.
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {
    Message findOneById(Integer id);
    Page<Message> findByReceiver(Pageable pageable, User user);
}
