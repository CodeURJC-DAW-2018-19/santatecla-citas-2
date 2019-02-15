package com.urjc.daw.practica.model;

import javax.persistence.*;

@Entity
@Table(name = "text")
public class Text extends Item{

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topicContaining;


    public Text() {
        //JPA NEEDS A DEFAULT CONSTRUCTOR
    }

    public Text(String text, Topic themecontaining) {
        this.text = text;
        this.topicContaining = themecontaining;
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

    public Topic getThemecontaining() {
        return topicContaining;
    }

    public void setThemecontaining(Topic themecontaining) {
        this.topicContaining = themecontaining;
    }
}
