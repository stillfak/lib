package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import com.github.sv.models.Book;
import com.github.sv.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("lib/libBooks")
public class BookLibRestController {

    private final BookServiceImpl service;

    private com.github.sv.Mapper.ModelMapper mapper;


    @Autowired
    public BookLibRestController(BookServiceImpl service) {
        this.service = service;
        this.mapper = new com.github.sv.Mapper.ModelMapper();
    }

    BookServiceImpl getService() {
        return service;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookDTO> getAllBooks() {//SpringDataWebProperties.Pageable pageable
        return service.getRepository().findAll().stream().map(book -> mapper.convertToDto(book)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public BookDTO getBook(@PathVariable Long id) {
        return mapper.convertToDto(service.findById(id).get());
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public BookDTO add(@RequestBody String name,
                       @RequestBody Long size,
                       @RequestBody String author) {
        return mapper.convertToDto(service.add(new Book(name, size, author)));

    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public BookDTO edit(@PathVariable Long id,
                        @RequestBody String name,
                        @RequestBody Long size,
                        @RequestBody String author,
                        @RequestBody Boolean availability) {
        service.findById(id).get().setBookName(name);
        service.findById(id).get().setAuthorBook(author);
        service.findById(id).get().setNumberOfPages(size);
        service.findById(id).get().setAvailability(availability);

        return mapper.convertToDto(service.findById(id).get());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public BookDTO delete(@PathVariable Long id) {
        return mapper.convertToDto(service.delete(service.findById(id).get()));
    }


}
