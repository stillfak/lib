package com.github.sv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sv.LibApplication;
import com.github.sv.dto.BookDTO;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibApplication.class)
public class BookLibRestControllerTest {

    @Autowired
    private BookLibRestController libRestController;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(wac)
//                .standaloneSetup(libRestController)
                .build();
    }

    @Test
    public void getAllBooks() throws Exception {
        mvc.perform(get("/lib/books")
                        .param("page","2")
                        .param("size","30")
                )
                .andExpect(jsonPath("$.*").value(hasSize(30)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getBook() throws Exception {
        long id = libRestController.add(new BookDTO("book2", 502L, "author2")).getId();

        mvc.perform(get("/lib/books/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("book2"))
                .andExpect(jsonPath("$.author").value("author2"))
                .andExpect(jsonPath("$.numberOfPages").value(502))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void add() throws Exception {

        BookDTO bookDTO = new BookDTO("Book1", 500L, "author51");
//        System.out.println(objectMapper.writeValueAsString(bookDTO));
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/lib/books")
                        .content(objectMapper.writeValueAsString(bookDTO))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8);

        mvc.perform(requestBuilder)
                .andExpect(jsonPath("$.name", is("Book1")))
                .andExpect(jsonPath("$.numberOfPages", is(500)))
                .andExpect(jsonPath("$.author", is("author51")));
//                .andDo(print());
//        mvc.perform(get("http://localhost:8080/lib/books/100")).andDo(print());

    }

    @Test
    public void edit() throws Exception {
        BookDTO bookDTO = libRestController.add(new BookDTO("",  0L, ""));

        bookDTO.setName("book");
        bookDTO.setAuthor("author1");
        bookDTO.setNumberOfPages(500L);
        long id = bookDTO.getId();

        mvc.perform(put("/lib/books/" + id)
                .content(objectMapper.writeValueAsString(bookDTO))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("book")))
                .andExpect(jsonPath("$.author", is("author1")))
                .andExpect(jsonPath("$.numberOfPages", is(500)));
//                .andDo(print());
    }

    @Test
    public void delete() throws Exception {
        long id = libRestController.add(new BookDTO("book", 500L, "author1")).getId();

        mvc.perform(MockMvcRequestBuilders.delete("/lib/books/" + id))
                .andExpect(jsonPath("$", is("Успешно")));
//              .andDo(print());
    }

}