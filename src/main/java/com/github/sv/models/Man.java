package com.github.sv.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Man {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;


    @OneToMany(targetEntity = Book.class, mappedBy = "man")
    @OrderBy
    private List<Book> booksOnHand; // books


    protected Man() {
    }

    public Man(String lastName) {
        this.lastName = lastName;
    }

    public Man(String lastName, List<Book> books) {
        this.lastName = lastName;
        this.booksOnHand = books;
    }

    public Man(String lastName, List<Book> books, Long id) {
        this.lastName = lastName;
        this.booksOnHand = books;
        this.id = id;
    }

    public Man(String lastName, Long id) {
        this.lastName = lastName;
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooksOnHand() {
        return booksOnHand;
    }

    public void setBooksOnHand(List<Book> booksOnHand) {
        this.booksOnHand = booksOnHand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return lastName + " id = " + id + ", count on hand = " + booksOnHand.size();// + ", count books on hand = " + booksOnHand
    }

}
