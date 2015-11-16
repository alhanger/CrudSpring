package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by alhanger on 11/12/15.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    Integer id;

    @Column(nullable = false)
    public String content;

    @ManyToOne
    public User sender;

    @ManyToOne
    public User receiver;
}
