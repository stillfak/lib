package com.github.sv.models;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookName;
    private String authorBook;
    @ManyToOne(targetEntity = Man.class)
    private Man man;

    private Long numberOfPages;


    protected Book() {

    }

    public Book(String bookName, Long numberOfPages, String authorBook) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
    }

    public Book(String bookName, Long numberOfPages, String authorBook, Long id, Man man) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
        this.id = id;
        this.man = man;
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

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public Man getMan() {
        return man;
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

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    @Override
    public String toString() {
        return bookName + " " + authorBook + " "
                + numberOfPages + " id=" + id +
                ", man=" + man;
    }
}
