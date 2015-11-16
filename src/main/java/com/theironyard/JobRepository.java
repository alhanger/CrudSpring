package com.theironyard;

import com.theironyard.entities.Job;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alhanger on 11/12/15.
 */
public interface JobRepository extends CrudRepository<Job, Integer> {
}
