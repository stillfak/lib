package com.github.sv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sv.LibApplication;
import com.github.sv.dto.ManDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibApplication.class)
public class ManLibRestControllerTest {

    @Autowired
    private ManLibRestController controller;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    //    private QPageRequest pageable = new QPageRequest(0,9);
    @Before
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(wac)
//                .standaloneSetup(libRestController)
                .build();
    }

    @Test
    public void getAllMan() throws Exception {
        mvc.perform(get("/lib/mans"))
                .andExpect(jsonPath("$.*").value(hasSize(20)));

    }

    @Test
    public void getMan() throws Exception {
        long id = controller.add(new ManDTO("man")).getId();
        mvc.perform(get("/lib/mans/" + id))
                .andExpect(jsonPath("$.lastName").value("man"));
    }

    @Test
    public void add() throws Exception {
        mvc.perform(post("/lib/mans")
                .content(objectMapper.writeValueAsString(new ManDTO("man")))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.lastName").value("man"))
                .andDo(print());
    }

    @Test
    public void edit() throws Exception {
        ManDTO man = controller.add(new ManDTO("sourceMan"));

        man.setBooksOnHand(new ArrayList<>());
        man.setLastName("man");

        mvc.perform(put("/lib/mans/" + man.getId())
                .content(objectMapper.writeValueAsString(man))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.lastName").value("man"))
                .andExpect(jsonPath("$.booksOnHand").value(new ArrayList<>()))
                .andDo(print());
    }

    @Test
    public void delete() throws Exception{
        long id = controller.add(new ManDTO("delete")).getId();

        mvc.perform(MockMvcRequestBuilders.delete("/lib/mans/"+id))
                .andExpect(jsonPath("$").value("Успешно"))
                .andDo(print());
    }
}