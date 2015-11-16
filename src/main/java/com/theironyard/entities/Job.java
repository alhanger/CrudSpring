package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by alhanger on 11/12/15.
 */
@Entity
public class Job {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    Integer id;

    @Column(nullable = false)
    public String company;

    @Column(nullable = false)
    public String position;

    @Column(nullable = false)
    public int yearsWorked;

    @OneToOne
    public User user;
}
