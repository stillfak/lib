package com.github.sv.service.impl;

import com.github.sv.TestStart;
import com.github.sv.models.Man;
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
@SpringBootTest(classes = TestStart.class)
public class ManServiceImplTest {

    @Autowired
    private ManServiceImpl manService;

    @Test
    public void add() {
        assertEquals(manService.getRepository().count(), 10);
        Man man = manService.add(new Man("man45"));
        assertEquals(man.getLastName(), "man45");
        assertEquals(manService.getRepository().count(), 11);

    }

    @Test
    public void delete() {
        int count = (int) manService.getRepository().count();
        Man man = manService.add(new Man("man 45"));
        assertEquals(manService.getRepository().count(), ++count);
        man = manService.delete(man);
        assertEquals(man.getLastName(),"man 45");
        assertEquals(manService.getRepository().count(), --count);
    }

    @Test
    public void find() {
        manService.find("man4").forEach(man ->
                assertEquals(man.getLastName(),"man4")
        );
    }

    @Test
    public void findById() {
        Man man = manService.findById((long) 15).get();
        assertEquals(man.getLastName(),"Man4");


    }
}