package com.theironyard.entities;

import com.theironyard.entities.Job;
import com.theironyard.entities.Message;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alhanger on 11/12/15.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    Integer id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @Column(nullable = false)
    public String location;

    @Column(nullable = false)
    public int age;

    @Column(nullable = false)
    public String music;

    @Column(nullable = false)
    public String vacation;

    @OneToOne (mappedBy = "user")
    public Job job;

    @OneToMany (mappedBy = "receiver")
    public List<Message> inbox;

    @OneToMany (mappedBy = "sender")
    public List<Message> outbox;

//    @OneToMany (mappedBy = "firstUser")
//    List<User> friends;
}
