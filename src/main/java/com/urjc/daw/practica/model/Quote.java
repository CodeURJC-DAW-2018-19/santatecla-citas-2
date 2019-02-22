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

    @Column(name = "quoteAuthor")
    private String author;

    @Column(name = "quoteBook")
    private String book;



    public Quote(){
        //JPA NEEDS DEFAULT CONSTRUCTOR
    }

    public Quote(String text, String author, String book){
        this.text=text;
        this.author=author;
        this.book=book;

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
