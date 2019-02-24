package com.urjc.daw.practica.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection
    private List<Long> quoteIds;

    @ElementCollection
    private List<String> texts;


    public Topic(){
        //JPA NEEDS A DEFAULT CONSTRUCTOR
    }

    public Topic(String name){
        this.name=name;
    }

    public Topic(String name, List<Quote> quotes){

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

    public boolean equals(Object o){
        Topic topic = (Topic) o;
        return this.getId().equals(topic.getId());
    }

}
