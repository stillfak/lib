package com.github.sv.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Man {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;
    @Lob
    private List<Book> booksOnHand; // books id


    protected Man() {}

    public Man(String lastName) {
//        this.booksOnHand = book;
        this.lastName = lastName;

    }

    public void setBooksOnHand(List<Book> booksOnHand) {
        this.booksOnHand = booksOnHand;
    }
//    public void setBooksOnHand(Book booksOnHand) {
//        this.booksOnHand = booksOnHand;
//    }

    @Override
    public String toString() {
        return lastName + " id = " + id + ", count on hand = "+ booksOnHand.size();// + ", count books on hand = " + booksOnHand
    }

}
