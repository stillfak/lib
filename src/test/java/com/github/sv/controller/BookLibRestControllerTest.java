package com.github.sv.controller;

import com.github.sv.LibApplication;
import com.github.sv.dto.BookDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibApplication.class)
public class BookLibRestControllerTest {

    @Autowired
    private BookLibRestController libRestController;


    private QPageRequest pageable = new QPageRequest(0, 20);

    @Test
    public void getAllBooks() {
        for (int i = 0; i < 21; i++) {
            libRestController.add(new BookDTO("book " + i, (long) 500 + i, "author " + i));
        }
        ArrayList<BookDTO> books = (ArrayList<BookDTO>) libRestController.getAllBooks(pageable);
        assertNotEquals(books.size(), 0);

    }

    @Test
    public void getBook() {
        long id = libRestController.add(new BookDTO("book2", (long) 502, "author2")).getId();

        BookDTO bookDTO = libRestController.getBook(id);
        assertEquals(bookDTO.getAuthorBook(), "author2");
        assertEquals(bookDTO.getBookName(), "book2");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 502);
    }

    @Test
    public void add() {
        BookDTO bookDTO = libRestController.add(new BookDTO("Book1", (long) 500, "author51"));

        assertEquals(bookDTO.getAuthorBook(), "author51");
        assertEquals(bookDTO.getBookName(), "Book1");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 500);

    }

    @Test
    public void edit() {
        BookDTO bookDTO1 = libRestController.add(new BookDTO("", (long) 0, ""));

        bookDTO1.setBookName("book");
        bookDTO1.setAuthorBook("author1");
        bookDTO1.setNumberOfPages((long) 500);

        long id = bookDTO1.getId();

        BookDTO bookDTO = libRestController.edit(id, bookDTO1);

        assertEquals(Math.toIntExact(bookDTO.getId()), id);
        assertEquals(bookDTO.getAuthorBook(), "author1");
        assertEquals(bookDTO.getBookName(), "book");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 500);
//        assertEquals(bookDTO.getAvailability(), true);
    }

    @Test
    public void delete() {
        long count = libRestController.count();

        BookDTO bookDTO = libRestController.add(new BookDTO("book", (long) 500, "author1"));
        assertEquals(Math.toIntExact(libRestController.count()), 1 + count);
        assertEquals(libRestController.delete(bookDTO.getId()), "Успешно");
        assertEquals(Math.toIntExact(libRestController.count()), count);
        System.out.println(libRestController.delete((long) 999));
    }

}