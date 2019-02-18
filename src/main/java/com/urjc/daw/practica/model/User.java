package com.urjc.daw.practica.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roles;

    public User(){
        //JPA NEEDS DEFAULT CONSTRUCTOR
    }

}
