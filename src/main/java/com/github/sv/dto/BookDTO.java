package com.github.sv.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BookDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookName;
    private String authorBook;
    private Boolean availability;
    private Long numberOfPages;

    public Long getId() {
        return id;
    }

    protected BookDTO() {
    }

    public BookDTO(String bookName, Long numberOfPages, String authorBook) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
        this.availability = false;
    }


    @Override
    public String toString() {
        return bookName + " " + authorBook + " "
                + numberOfPages + " id=" + id +
                ", availability=" + availability + '}';
    }
}
