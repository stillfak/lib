package com.github.sv.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Man {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;

    @Lob
    private List<Book> booksOnHand; // books id


    protected Man() {
    }

    public Man(String lastName) {
        this.lastName = lastName;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return lastName + " id = " + id + ", count on hand = " + booksOnHand.size();// + ", count books on hand = " + booksOnHand
    }

}
