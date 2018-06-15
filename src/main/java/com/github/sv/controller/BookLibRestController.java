package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import com.github.sv.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("lib/libBooks")
public class BookLibRestController {

    private final BookServiceImpl service;

    private com.github.sv.mapper.ModelMapper mapper;


    @Autowired
    public BookLibRestController(BookServiceImpl service) {
        this.service = service;
        this.mapper = new com.github.sv.mapper.ModelMapper();
    }

    BookServiceImpl getService() {
        return service;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookDTO> getAllBooks() {//SpringDataWebProperties.Pageable pageable
        return service.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public BookDTO getBook(@PathVariable Long id) {
        return mapper.convertToDto(service.findById(id));
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public BookDTO add(@RequestBody BookDTO newBook) {
        return mapper.convertToDto(service.add(mapper.convertToEnable(newBook)));

    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public BookDTO edit(@PathVariable Long id,
                        @RequestBody BookDTO editBook) {
        return mapper.convertToDto(service.update(mapper.convertToEnable(editBook)));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public BookDTO delete(@PathVariable Long id) {
        return mapper.convertToDto(service.deleteById(id));
    }
}