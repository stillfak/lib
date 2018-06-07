package com.github.sv.controller;

import com.github.sv.LibApplication;
import com.github.sv.dto.BookDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ManLibRestControllerTest {

    @Autowired
    private ManLibRestController controller;


    @Test
    public void getAllMan() {
        for (int i = 0; i < 10; i++) {
            controller.add("Man" + i);
        }
        assertEquals(controller.getAllMan().size(), 10);

    }

    @Test
    public void getMan() {
        long id = controller.add("man").getId();

        assertEquals(controller.getMan(id).getLastName(), "man");
    }

    @Test
    public void add() {
        long id = controller.add("man").getId();
        assertEquals(controller.getMan(id).getLastName(),"man");
    }

    @Test
    public void edit() {
    }

    @Test
    public void delete() {
    }
}