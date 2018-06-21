package com.github.sv.dto;

public class BookDTO {

    private Long id;

    private String name;
    private String author;
    private ManDTO man;
    private Long numberOfPages;

    public BookDTO() {
    }

    public BookDTO(String name, Long numberOfPages, String authorBook) {
        this.name = name;
        this.author = authorBook;
        this.numberOfPages = numberOfPages;
    }

    public BookDTO(String name, Long numberOfPages, String author, Long id, ManDTO man) {
        this.name = name;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.id = id;
        this.man = man;
    }

    public void setMan(ManDTO man) {
        this.man = man;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public ManDTO getMan() {
        return man;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public String toString() {
        return name + " " + author + " "
                + numberOfPages + " id=" + id +
                ", Man " + man;
    }
}
