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


    protected BookDTO() {
    }

    public BookDTO(String bookName, Long numberOfPages, String authorBook) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
        this.availability = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Long getId() {

        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public String toString() {
        return bookName + " " + authorBook + " "
                + numberOfPages + " id=" + id +
                ", availability=" + availability;
    }
}
