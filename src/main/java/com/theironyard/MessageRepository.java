package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by alhanger on 11/12/15.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
