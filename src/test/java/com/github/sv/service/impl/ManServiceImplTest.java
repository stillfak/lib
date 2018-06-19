package com.github.sv.service.impl;

import com.github.sv.LibApplication;
import com.github.sv.models.Man;
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
public class ManServiceImplTest {

    @Autowired
    private ManServiceImpl manService;

    @Test
    public void add() {
        assertEquals(manService.count(), 0);
        Man man = manService.add(new Man("man45"));
        assertEquals(man.getLastName(), "man45");
        assertEquals(manService.count(), 1);

    }

    @Test
    public void delete() {
        int count = (int) manService.count();
        Man man = manService.add(new Man("man 45"));
        assertEquals(manService.count(), count + 1);
        manService.deleteById(man.getId());
        assertEquals(man.getLastName(), "man 45");
        assertEquals(manService.count(), count);
    }

    @Test
    public void find() {
        manService.add(new Man("man4"));
        manService.find("man4").forEach(man -> assertEquals(man.getLastName(), "man4"));
    }

    @Test
    public void findById() {
        long id = manService.add(new Man("man")).getId();
        assertEquals(manService.findById(id).getLastName(), "man");


    }
}