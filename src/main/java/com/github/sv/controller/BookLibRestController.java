package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import com.github.sv.models.Book;
import com.github.sv.service.impl.BookServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("lib/libBooks")
public class BookLibRestController {

    private final BookServiceImpl service;

    private ModelMapper modelMapper;



    @Autowired
    public BookLibRestController(BookServiceImpl service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
//        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }
//    public BookLibRestController() {}

    BookServiceImpl getService() {
        return service;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookDTO> getAllBooks(SpringDataWebProperties.Pageable pageable) {
        return service.getRepository().findAll(/**pageable.getMaxPageSize()*/).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public BookDTO getBook(@PathVariable Long id) {
        return convertToDto(service.findById(id).get());
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public BookDTO add(@RequestBody String name,
                       @RequestBody Long size,
                       @RequestBody String author) {
        return convertToDto(service.add(new Book(name, size, author)));

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


        return convertToDto(service.findById(id).get());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public BookDTO delete(@PathVariable Long id) {
        return convertToDto(service.delete(service.findById(id).get()));
    }

    private BookDTO convertToDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    private Book convertToEnable(BookDTO sourceObject) {
        return modelMapper.map(sourceObject, Book.class);
    }


}
