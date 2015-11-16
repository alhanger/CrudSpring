package com.theironyard;

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
    Integer id;

    String username;
    String password;
    String firstName;
    String lastName;
    String location;
    int age;
    String music;
    String vacation;

    @OneToOne (mappedBy = "user")
    Job job;

    @OneToMany (mappedBy = "receiver")
    List<Message> inbox;

    @OneToMany (mappedBy = "sender")
    List<Message> outbox;

//    @OneToMany (mappedBy = "firstUser")
//    List<User> friends;
}
