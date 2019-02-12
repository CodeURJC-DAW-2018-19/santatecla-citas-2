package com.urjc.daw.practica.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "themes")
    private List<Quote> quotes;


    public Theme(){
        //JPA NEEDS A DEFAULT CONSTRUCTOR
    }

    public Theme(String name){
        this.name=name;
    }

    public Theme(String name, List<Quote> quotes){
        this.quotes= quotes;
        this.name= name;
    }
    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
