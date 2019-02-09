package com.urjc.daw.practica.model;

import javax.persistence.*;

@Entity
@Table(name = "text")
public class Text {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme themeContaining;


    public Text() {
        //JPA NEEDS A DEFAULT CONSTRUCTOR
    }

    public Text(String text, Theme themecontaining) {
        this.text = text;
        this.themeContaining = themecontaining;
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

    public Theme getThemecontaining() {
        return themeContaining;
    }

    public void setThemecontaining(Theme themecontaining) {
        this.themeContaining = themecontaining;
    }
}
