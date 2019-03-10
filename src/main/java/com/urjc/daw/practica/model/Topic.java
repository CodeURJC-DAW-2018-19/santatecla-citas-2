package com.urjc.daw.practica.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private int nQuotes = 0;

    @ElementCollection
    private List<Long> quoteIds;

    @ElementCollection
    private List<String> texts;


    public Topic(){
        //JPA NEEDS A DEFAULT CONSTRUCTOR
    }

    public Topic(String name){
        if (name==null){
            this.name="";
        }else {
            this.name = name;
        }
    }

    public Topic(String name, List<Quote> quotes){
        this.name= name;
        this.nQuotes = quotes.size();
    }
    
    public Topic(String name,List<Long> quote,List<String> texts) {
    	this.name = name;
    	this.quoteIds = quote;
    	this.texts = texts;
    }
    
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name==null){
            this.name="";
        }else {
            this.name = name;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getQuoteIds() {
        return quoteIds;
    }

    public void setQuoteIds(List<Long> quoteIds) {
        this.quoteIds = quoteIds;
        this.nQuotes = quoteIds.size();
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public boolean equals(Object o){
        Topic topic = (Topic) o;
        return this.getId().equals(topic.getId());
    }

}
