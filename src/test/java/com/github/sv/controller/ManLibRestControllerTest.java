package com.github.sv.controller;

import com.github.sv.LibApplication;
import com.github.sv.dto.BookDTO;
import com.github.sv.dto.ManDTO;
import com.github.sv.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibApplication.class)
public class ManLibRestControllerTest {

    @Autowired
    private ManLibRestController controller;
    @Autowired
    private BookLibRestController bookController;


    private QPageRequest pageable = new QPageRequest(0,9);

    @Test
    public void getAllMan() {
        for (int i = 0; i < 10; i++) {
            controller.add(new ManDTO("Man" + i));
        }
        assertEquals(controller.getAllMan(pageable).size(), 9);

    }

    @Test
    public void getMan() {
        long id = controller.add(new ManDTO("man")).getId();

        assertEquals(controller.getMan(id).getLastName(), "man");
    }

    @Test
    public void add() {
        long id = controller.add(new ManDTO("man")).getId();
        assertEquals(controller.getMan(id).getLastName(), "man");
    }

    @Test
    public void edit() {
        BookDTO[] book = new BookDTO[10];

        for (int i = 0; i < book.length; i++) {
            book[i] = bookController.add(new BookDTO("book" + i, (long) 500 + i, "author " + i));
        }
        List<BookDTO> dto = Arrays.asList(book);
        ManDTO manDTO = new ManDTO("manTest", dto,null);
        long id = controller.add(manDTO).getId();
        controller.edit(id, new ManDTO("man",dto,null));
        manDTO = controller.getMan(id);
        manDTO.getBooksOnHand().forEach(System.out::println);
//        assertEquals(controller.getMan(id).getLastName(), "man");
    }

    @Test
    public void delete() {
        long id = controller.add(new ManDTO("delete")).getId();
        System.out.println(controller.delete(id));
        try {
            assertNull(controller.getMan(id));
        }catch (NotFoundException e){
            System.out.println("Не найдено");
        }

    }
}