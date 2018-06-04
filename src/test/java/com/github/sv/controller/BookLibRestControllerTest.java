package com.github.sv.controller;

import com.github.sv.TestStart;
import com.github.sv.dto.BookDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestStart.class)
public class BookLibRestControllerTest {

    @Autowired
    private BookLibRestController libRestController;


    @Test
    public void getAllBooks() {
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        ArrayList<BookDTO> books = (ArrayList<BookDTO>) libRestController.getAllBooks(pageable);
        for (int i = 0; i < libRestController.getService().getRepository().count(); i++) {
            assertEquals(books.get(i).getAuthorBook(), "author" + i);
            assertEquals(books.get(i).getBookName(), "book" + i);
            assertEquals(Math.toIntExact(books.get(i).getId()), i + 1);
            assertEquals(Math.toIntExact(books.get(i).getNumberOfPages()), 500 + i);
            assertEquals(books.get(i).getAvailability(), false);
        }
    }

    @Test
    public void getBook() {
        BookDTO bookDTO = libRestController.getBook((long) 3);
        assertEquals(bookDTO.getAuthorBook(), "author2");
        assertEquals(bookDTO.getBookName(), "book2");
        assertEquals(Math.toIntExact(bookDTO.getId()), 3);
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 502);
        assertEquals(bookDTO.getAvailability(), false);
    }

    @Test
    public void add() {
        BookDTO bookDTO = libRestController.add("Book1", (long) 500, "author51");

        assertEquals(bookDTO.getAuthorBook(), "author51");
        assertEquals(bookDTO.getBookName(), "Book1");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 500);
        assertEquals(bookDTO.getAvailability(), false);

    }

    @Test
    public void edit() {
        BookDTO bookDTO = libRestController.edit((long) 2,"book_6",(long) 660,"author.asd",true);

        assertEquals(bookDTO.getAuthorBook(), "author.asd");
        assertEquals(bookDTO.getBookName(), "book_6");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 660);
        assertEquals(bookDTO.getAvailability(), true);
    }

    @Test
    public void delete() {
    }

    @Test
    public void convertToDto() {
    }

    @Test
    public void convertToEnable() {
    }
}