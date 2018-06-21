package com.github.sv.controller;

import com.github.sv.dto.ManDTO;
import com.github.sv.exception.NotFoundException;
import com.github.sv.mapper.ModelMapper;
import com.github.sv.service.impl.ManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("lib/mans")
public class ManLibRestController {

    private final ManServiceImpl service;

    private final ModelMapper mapper;

    @Autowired
    public ManLibRestController(ManServiceImpl service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ManDTO> getAllMans(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = new QPageRequest(page, size);
        return service.findAll(pageable)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMan(@PathVariable Long id) {
        try {
            return mapper.convertToDto(service.findById(id)).toString();
        } catch (com.github.sv.exception.NotFoundException e) {
            return "Не найдено";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ManDTO add(@RequestBody ManDTO newMan) {
        return mapper.convertToDto(service.add(mapper.convertToEnable(newMan)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ManDTO edit(@PathVariable Long id,
                       @RequestBody ManDTO editMan) {
        editMan.setId(id);
        return mapper.convertToDto(
                service.update(
                        mapper.convertToEnable(editMan)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return "Успешно";
        } catch (NotFoundException e) {
            return "Не найдено";
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long count() {
        return service.count();
    }
}
