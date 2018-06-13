package com.github.sv.mapper;

import com.github.sv.LibApplication;
import com.github.sv.dto.BookDTO;
import com.github.sv.dto.ManDTO;
import com.github.sv.models.Book;
import com.github.sv.models.Man;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibApplication.class)
public class ModelMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void convertToDto() {
        BookDTO bookDTO = mapper.convertToDto(new Book("book",(long) 500,"author"));
        System.out.println(bookDTO);
        assertEquals(bookDTO.getBookName(),"book");
        assertEquals(Math.toIntExact(bookDTO.getNumberOfPages()),500);
        assertEquals(bookDTO.getAuthorBook(), "author");
        assertEquals(bookDTO.getAvailability(),false);
    }

    @Test
    public void convertToEnable() {
        Book book = mapper.convertToEnable(new BookDTO("book",(long) 500,"author"));
        System.out.println(book);
        assertEquals(book.getBookName(),"book");
        assertEquals(Math.toIntExact(book.getNumberOfPages()),500);
        assertEquals(book.getAuthorBook(), "author");
        assertEquals(book.getAvailability(),false);
    }

    @Test
    public void convertToDto1() {
        ManDTO manDTO = mapper.convertToDto(new Man("Man"));
        System.out.println(manDTO);
        assertEquals(manDTO.getLastName(),"Man");
        assertEquals(manDTO.getBooksOnHand(),new ArrayList<>());
    }

    @Test
    public void convertToEnable1() {
        Man man = mapper.convertToEnable(new ManDTO("Man"));
        System.out.println(man);
        assertEquals(man.getLastName(),"Man");
        assertEquals(man.getBooksOnHand(),new ArrayList<>());
    }
}