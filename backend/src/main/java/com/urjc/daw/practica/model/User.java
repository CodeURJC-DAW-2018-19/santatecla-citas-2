package com.urjc.daw.practica.model;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roles;

    public User(){
        //JPA NEEDS THIS
    }

    public User(String name, String password, String... roles) {
		this.userName = name;
		this.password = password;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String toString(){
        if(this.roles.contains("ROLE_ADMIN")){
            return "admin";
        }else{
            return "user";
        }
    }
}
