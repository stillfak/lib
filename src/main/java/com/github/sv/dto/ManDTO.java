package com.github.sv.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

public class ManDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;

    @Lob
    private List<BookDTO> booksOnHand; // books id


    protected ManDTO() {}

    public ManDTO(String lastName) {
        this.booksOnHand = new ArrayList<>();
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public List<BookDTO> getBooksOnHand() {
        return booksOnHand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooksOnHand(List<BookDTO> booksOnHand) {
        this.booksOnHand = booksOnHand;
    }

    @Override
    public String toString() {
        return lastName + " id = " + id + ", count on hand = "+ booksOnHand.size();// + ", count books on hand = " + booksOnHand
    }
}
