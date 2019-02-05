package com.urjc.daw.practica.model;

import javax.persistence.*;

@Entity
public class Quote {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quoteText")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "book")
    private String book;

    public Quote(){
        //JPA NEEDS DEFAULT CONSTRUCTOR
    }

    public Quote(String text, String author, String book){

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
}
