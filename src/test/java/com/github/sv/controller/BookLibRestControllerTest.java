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

//    @Autowired
//    public BookLibRestControllerTest(BookLibRestController libRestController) {
//        this.libRestController = libRestController;
//    }

    @Test
    public void getAllBooks() {
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
        ArrayList<BookDTO> books = (ArrayList<BookDTO>) libRestController.getAllBooks(pageable);
        for (int i = 0; i < libRestController.getService().getRepository().count(); i++) {
            assertEquals(books.get(i).getAuthorBook(),"author"+i);
            assertEquals(books.get(i).getBookName(),"book"+i);
            assertEquals(Math.toIntExact(books.get(i).getId()) ,i+1);
            assertEquals(Math.toIntExact(books.get(i).getNumberOfPages()), 600+i);
            assertEquals(books.get(i).getAvailability(),false);
        }
    }

    @Test
    public void getBook() {
        BookDTO bookDTO = libRestController.getBook((long)3);
        assertEquals(bookDTO.getAuthorBook(),"author2");
        assertEquals(bookDTO.getBookName(),"book2");
        assertEquals(Math.toIntExact(bookDTO.getId()) ,3);
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()), 602);
        assertEquals(bookDTO.getAvailability(),false);
    }

    @Test
    public void add() {

    }

    @Test
    public void edit() {
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