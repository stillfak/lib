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
        assertEquals(bookService.getRepository().count(), 1);
        assertEquals(book.getBookName(), "book");
        assertEquals(book.getAuthorBook(), "author");
        assertEquals(Math.toIntExact(book.getId()), 1);
        assertEquals(book.getAvailability(), false);
        assertEquals(Math.toIntExact(book.getNumberOfPages()), 500);


    }

    @Test
    public void delete() {
        Book book = bookService.add(new Book("book", (long) 500, "author"));
        assertEquals(bookService.getRepository().count(), 1);
        bookService.delete(book);
        assertEquals(bookService.getRepository().count(), 0);
    }

    @Test
    public void find() {
        for (int i = 0; i < 10; i++) {
            bookService.add(new Book("book" + i, (long) 500 + i, "author" + i));
        }

        bookService.find("book1").forEach(book -> {
            assertEquals(book.getAuthorBook(), "author1");
            assertEquals(book.getBookName(), "book1");
            assertEquals(Math.toIntExact(book.getNumberOfPages()), 501);
            assertEquals(book.getAvailability(), false);
        });

    }

    @Test
    public void findById() {
        for (int i = 0; i < 10; i++) {
            bookService.add(new Book("book" + i, (long) 500 + i, "author" + i));
        }
        Book book = bookService.findById((long) 5).get();

        assertEquals(Math.toIntExact(book.getId()), 5);
        assertEquals(book.getBookName(), "book4");
        assertEquals(Math.toIntExact(book.getNumberOfPages()), 504);
        assertEquals(book.getAvailability(), false);
        assertEquals(book.getAuthorBook(), "author4");

    }
}