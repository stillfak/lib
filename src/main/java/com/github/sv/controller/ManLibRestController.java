package com.github.sv.controller;

import com.github.sv.dto.ManDTO;
import com.github.sv.exception.NotFoundException;
import com.github.sv.mapper.ModelMapper;
import com.github.sv.service.impl.ManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public Page<ManDTO> getAllMans(Pageable pageable) {
        return service.findAll(pageable)
                .map(mapper::convertToDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ManDTO getMan(@PathVariable Long id) {
        return mapper.convertToDto(service.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ManDTO add(@RequestBody ManDTO newMan) {
        return mapper.convertToDto(service.addOrUpdate(mapper.convertToEnable(newMan)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ManDTO edit(@PathVariable Long id,
                       @RequestBody ManDTO editMan) {
        editMan.setId(id);
        return mapper.convertToDto(
                service.addOrUpdate(
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
