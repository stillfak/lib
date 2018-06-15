package com.github.sv.dto;

public class BookDTO {

    private Long id;

    private String bookName;
    private String authorBook;
    private ManDTO manDTO;
    private Long numberOfPages;


    protected BookDTO() {
    }

    public BookDTO(String bookName, Long numberOfPages, String authorBook) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
    }

    public BookDTO(String bookName, Long numberOfPages, String authorBook, Long id) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
        this.id = id;
    }

    public BookDTO(String bookName, Long numberOfPages, String authorBook, Long id, ManDTO man) {
        this.bookName = bookName;
        this.authorBook = authorBook;
        this.numberOfPages = numberOfPages;
        this.id = id;
        this.manDTO = man;
    }

    public void setManDTO(ManDTO manDTO) {
        this.manDTO = manDTO;
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

    public ManDTO getManDTO() {
        return manDTO;
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


    @Override
    public String toString() {
        return bookName + " " + authorBook + " "
                + numberOfPages + " id=" + id +
                ", Man" + manDTO;
    }
}
