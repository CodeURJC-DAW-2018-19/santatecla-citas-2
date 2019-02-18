<<<<<<< HEAD:src/main/java/com/urjc/daw/practica/model/Topic.java
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
=======
package com.urjc.daw.practica.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theme")
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    private String name;



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
>>>>>>> fd5012c0c418b2cb06b0a07088eb28ef975c7f2a:src/main/java/com/urjc/daw/practica/model/Topic.java
