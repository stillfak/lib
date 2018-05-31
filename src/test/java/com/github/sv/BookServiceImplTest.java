package com.github.sv;

import com.github.sv.models.Book;
import com.github.sv.service.impl.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

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
        Book book = bookService.add(new Book("book",(long) 500,"author"));
        assertEquals(book.getBookName(),"book");
        assertEquals(book.getAuthorBook(),"author");
        assertEquals(Math.toIntExact(book.getId()),1);
        assertEquals(book.getAvailability(),false);
        assertEquals(Math.toIntExact(book.getNumberOfPages()),500);


    }

    @Test
    public void delete() {

    }

    @Test
    public void find() {

    }

    @Test
    public void findById() {

    }
}