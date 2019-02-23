package com.urjc.daw.practica.model;

import javax.persistence.*;

@Entity
@Table(name = "text")
public class Text extends Item{

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;


    public Text() {
        //JPA NEEDS A DEFAULT CONSTRUCTOR
    }

    public Text(String text, Topic topiccontaining) {
        this.text = text;
        this.topic = topiccontaining;
    }

    public Long getId() {
        return id;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Topic getTopiccontaining() {
        return topic;
    }

    public void setTopiccontaining(Topic topiccontaining) {
        this.topic = topiccontaining;
    }
}
