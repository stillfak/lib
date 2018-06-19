package com.github.sv.service.impl;

import com.github.sv.LibApplication;
import com.github.sv.models.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibApplication.class)
public class BookServiceImplTest {
    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void add() {
        Book book = bookService.add(new Book("book", (long) 500, "author"));
        assertEquals(bookService.count(), 1);
        assertEquals(book.getBookName(), "book");
        assertEquals(book.getAuthorBook(), "author");
//        assertEquals(book.getAvailability(), false);
        assertEquals(Math.toIntExact(book.getNumberOfPages()), 500);


    }

    @Test
    public void delete() {
        Book book = bookService.add(new Book("book", (long) 500, "author"));
        assertEquals(bookService.count(), 1);
        bookService.deleteById(book.getId());
        assertEquals(bookService.count(), 0);
    }

    @Test
    public void find() {
        bookService.find("book1").forEach(book -> {
            assertEquals(book.getAuthorBook(), "author1");
            assertEquals(book.getBookName(), "book1");
            assertEquals(Math.toIntExact(book.getNumberOfPages()), 501);
//            assertEquals(book.getAvailability(), false);
        });
    }

        @Test
    public void findById() {
        long id = bookService.add(new Book("book",(long) 666,"author")).getId();
        Book book = bookService.findById(id);

        assertEquals(book.getBookName(), "book");
        assertEquals(Math.toIntExact(book.getNumberOfPages()), 666);
//        assertEquals(book.getAvailability(), false);
        assertEquals(book.getAuthorBook(), "author");

    }
    @Test
    public void update() {
        Book book = bookService.add(new Book("", (long) 0, ""));
        book.setNumberOfPages((long) 1234567);
        book.setAuthorBook("author");
        book.setBookName("r2d2");
        book = bookService.update(book);

        assertEquals(book.getBookName(),"r2d2");
        assertEquals(book.getAuthorBook(),"author");
        assertEquals(Math.toIntExact(book.getNumberOfPages()),1234567);

    }
}