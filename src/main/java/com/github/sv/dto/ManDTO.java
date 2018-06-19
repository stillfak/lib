package com.github.sv.dto;

import java.util.List;

public class ManDTO {

    private Long id;

    private String lastName;

    private List<BookDTO> booksOnHand; // books id


    protected ManDTO() {
    }


    public ManDTO(String lastName) {
        this.lastName = lastName;
    }



    public ManDTO(String lastName, List<BookDTO> booksOnHand, Long id) {
        this.booksOnHand = booksOnHand;
        this.lastName = lastName;
        this.id = id;
    }



    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BookDTO> getBooksOnHand() {
        return booksOnHand;
    }

    public void setBooksOnHand(List<BookDTO> booksOnHand) {
        this.booksOnHand = booksOnHand;
    }

    @Override
    public String toString() {
        return lastName + " id = " + id + ", count on hand = " + booksOnHand.size();// + ", count books on hand = " + booksOnHand
    }
}
