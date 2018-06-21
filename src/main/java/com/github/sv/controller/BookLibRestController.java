package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import com.github.sv.exception.NotFoundException;
import com.github.sv.mapper.ModelMapper;
import com.github.sv.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "lib/books")
public class BookLibRestController {

    private final BookServiceImpl service;

    private final ModelMapper mapper;


    @Autowired
    public BookLibRestController(BookServiceImpl service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<BookDTO> getAllBooks(@RequestParam int page, @RequestParam int size) {
        QPageRequest pageable = new QPageRequest(page, size);
        return service.findAll(pageable)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getBook(@PathVariable Long id) {
        try {
            return mapper.convertToDto(service.findById(id)).toString();
        } catch (NotFoundException e) {
            return "Не найдено";
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public BookDTO add(@RequestBody BookDTO newBook) {
        return mapper.convertToDto(service.add(mapper.convertToEnable(newBook)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BookDTO edit(@PathVariable Long id,
                        @RequestBody BookDTO editBook) {
        return mapper.convertToDto(service.update(mapper.convertToEnable(editBook)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return "Успешно";
        } catch (Exception e) {
            return "Не найдено";
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long count() {
        return service.count();
    }
}