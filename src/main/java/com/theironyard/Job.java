package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by alhanger on 11/12/15.
 */
@Entity
public class Job {
    @Id
    @GeneratedValue
    Integer id;

    String company;
    String position;
    int yearsWorked;

    @OneToOne
    User user;
}
