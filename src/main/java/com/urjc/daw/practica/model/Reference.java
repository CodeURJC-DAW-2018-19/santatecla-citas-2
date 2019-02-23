package com.urjc.daw.practica.model;

import javax.persistence.*;

@Entity
public class Reference extends Item{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quoteReferenced;

    @ManyToOne(cascade =  CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic", nullable = false)
    private Topic topic;

    public Reference (){
        //JPA NEEDS THIS
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quote getQuoteReferenced() {
        return quoteReferenced;
    }

    public void setQuoteReferenced(Quote quoteReferenced) {
        this.quoteReferenced = quoteReferenced;
    }

    public Topic getTopicContaining() {
        return topic;
    }

    public void setTopicContaining(Topic topicContaining) {
        this.topic = topicContaining;
    }
}
