package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import com.github.sv.dto.ManDTO;
import com.github.sv.models.Man;
import com.github.sv.service.impl.ManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("lib/libMans")
public class ManLibRestController {

    private final ManServiceImpl service;
    private com.github.sv.Mapper.ModelMapper mapper;

    @Autowired
    public ManLibRestController(ManServiceImpl service) {
        this.service = service;
        this.mapper = new com.github.sv.Mapper.ModelMapper();
    }

    @RequestMapping(value = "/mans", method = RequestMethod.GET)
    public List<ManDTO> getAllMan(SpringDataWebProperties.Pageable pageable) {
        return service.getRepository().findAll().stream().map(man ->  mapper.convertToDto(man)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/mans/{id}", method = RequestMethod.GET)
    public ManDTO getMan(@PathVariable Long id) {
        return mapper.convertToDto(service.findById(id).get());
    }

    @RequestMapping(value = "/mans/{id}/books")
    public List<BookDTO> getBooksOnHand(@PathVariable Long id){
        return service.findById(id).get().getBooksOnHand().stream().map(book -> mapper.convertToDto(book)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/mans", method = RequestMethod.POST)
    public ManDTO add(String lastName) {
        return mapper.convertToDto(service.add(new Man(lastName)));
    }

    @RequestMapping(value = "/mans/{id}", method = RequestMethod.PUT)
    public ManDTO edit(@PathVariable Long id,
                       @RequestBody String lastName) {
           service.findById(id).get().setLastName(lastName);
        return null ;
    }

    @RequestMapping(value = "mans/{id}", method = RequestMethod.DELETE)
    public ManDTO delete(@PathVariable Long id) {
        return mapper.convertToDto(service.delete(service.findById(id).get()));
    }


}
