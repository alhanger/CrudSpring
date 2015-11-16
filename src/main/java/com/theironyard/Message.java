package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by alhanger on 11/12/15.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    Integer id;

    String content;

    @ManyToOne
    User sender;

    @ManyToOne
    User receiver;
}
