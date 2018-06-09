package com.github.sv.controller;

import com.github.sv.LibApplication;
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
@SpringBootTest(classes = LibApplication.class)
public class BookLibRestControllerTest {

    @Autowired
    private BookLibRestController libRestController;


    @Test
    public void getAllBooks() {
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        ArrayList<BookDTO> books = (ArrayList<BookDTO>) libRestController.getAllBooks();
        for (int i = 0; i < books.size(); i++) {
            assertEquals(books.get(i).getAuthorBook(), "author" + i);
            assertEquals(books.get(i).getBookName(), "book" + i);
            assertEquals(Math.toIntExact(books.get(i).getId()), i + 1);
            assertEquals(Math.toIntExact(books.get(i).getNumberOfPages()), 500 + i);
            assertEquals(books.get(i).getAvailability(), false);
        }

    }

    @Test
    public void getBook() {
        BookDTO bookDTO = libRestController.add( new BookDTO("book2",(long) 502,"author2"));
        assertEquals(bookDTO.getAuthorBook(), "author2");
        assertEquals(bookDTO.getBookName(), "book2");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 502);
        assertEquals(bookDTO.getAvailability(), false);
    }

    @Test
    public void add() {
        BookDTO bookDTO = libRestController.add(new BookDTO("Book1", (long) 500, "author51"));

        assertEquals(bookDTO.getAuthorBook(), "author51");
        assertEquals(bookDTO.getBookName(), "Book1");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 500);
        assertEquals(bookDTO.getAvailability(), false);

    }

    @Test
    public void edit() {
        BookDTO bookDTO1 = libRestController.add(new BookDTO("",(long) 0,""));

        bookDTO1.setBookName("book");
        bookDTO1.setAuthorBook("author1");
        bookDTO1.setNumberOfPages((long) 500);

        long id = bookDTO1.getId();

        BookDTO bookDTO = libRestController.edit(id,bookDTO1);

        assertEquals(Math.toIntExact(bookDTO.getId()),id);
        assertEquals(bookDTO.getAuthorBook(), "author1");
        assertEquals(bookDTO.getBookName(), "book");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 500);
//        assertEquals(bookDTO.getAvailability(), true);
    }

    @Test
    public void delete() {
        int count = (int) libRestController.getService().getRepositoryCount();

        BookDTO bookDTO = libRestController.add(new BookDTO("book", (long) 500, "author1"));
        assertEquals(libRestController.getService().getRepositoryCount(), 1 + count);
        BookDTO bookDTO1 = libRestController.delete(bookDTO.getId());

        assertEquals(bookDTO.getAvailability(),bookDTO1.getAvailability());
        assertEquals(bookDTO.getNumberOfPages(),bookDTO1.getNumberOfPages());
        assertEquals(bookDTO.getId(),bookDTO1.getId());
        assertEquals(bookDTO.getBookName(),bookDTO1.getBookName());
        assertEquals(bookDTO.getAuthorBook(),bookDTO1.getAuthorBook());

        assertEquals(libRestController.getService().getRepositoryCount(), count);

    }

}