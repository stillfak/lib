package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
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
public class BookLibRestControllerTest {


    private BookLibRestController libRestController;

//    @Autowired
//    public BookLibRestControllerTest(BookLibRestController libRestController) {
//        this.libRestController = libRestController;
//    }

    @Test
    public void getAllBooks() {
        SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();

        ArrayList<BookDTO> books = (ArrayList<BookDTO>) libRestController.getAllBooks(pageable);
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//                bookRepository.save(new Book("book" + i, (long) 600 + i, "author" + i));
             bookDTOS.add(new BookDTO("book" + i, (long) 600 + i, "author" + i));
        }
        assertEquals(books, bookDTOS);

    }

    @Test
    public void getBook() {
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