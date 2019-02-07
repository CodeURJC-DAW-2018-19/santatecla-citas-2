package com.urjc.daw.practica.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue
    @Column(name = "quote_id")
    private Long id;

    @Column(name = "quoteText")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "book")
    private String book;


    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "Quotes_Themes",
            joinColumns = { @JoinColumn(name = "quote_id") },
            inverseJoinColumns = { @JoinColumn(name = "theme_id") }
    )
    private List<Theme> themes;

    public Quote(){
        //JPA NEEDS DEFAULT CONSTRUCTOR
        this.themes= new ArrayList<>();
    }

    public Quote(String text, String author, String book){
        this.text=text;
        this.author=author;
        this.book=book;
        this.themes= new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Long getId() {
        return this.id;
    }

    public void addTheme(Theme theme){
        this.themes.add(theme);
    }
}
