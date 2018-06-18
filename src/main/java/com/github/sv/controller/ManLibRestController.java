package com.github.sv.controller;

import com.github.sv.dto.ManDTO;
import com.github.sv.mapper.ModelMapper;
import com.github.sv.service.impl.ManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("lib/libMans")
public class ManLibRestController {

    private final ManServiceImpl service;

    private ModelMapper mapper;

    @Autowired
    public ManLibRestController(ManServiceImpl service) {
        this.service = service;
        this.mapper = new ModelMapper();
    }

    @RequestMapping(value = "/mans", method = RequestMethod.GET)
    public List<ManDTO> getAllMan() {//SpringDataWebProperties.Pageable pageable
        return service.findAll().stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/mans/{id}", method = RequestMethod.GET)
    public ManDTO getMan(@PathVariable Long id) {
        return mapper.convertToDto(service.findById(id).orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }

    @RequestMapping(value = "/mans", method = RequestMethod.POST)
    public ManDTO add(@RequestBody ManDTO newMan) {
        return mapper.convertToDto(service.add(mapper.convertToEnable(newMan)));
    }

    @RequestMapping(value = "/mans/{id}", method = RequestMethod.PUT)
    public ManDTO edit(@PathVariable Long id,
                       @RequestBody ManDTO editMan) {
        editMan.setId(id);
        return mapper.convertToDto(service.update(mapper.convertToEnable(editMan)));
    }

    @RequestMapping(value = "mans/{id}", method = RequestMethod.DELETE)
    public ManDTO delete(@PathVariable Long id) {
        return mapper.convertToDto(service.delete(service.findById(id).get()));
    }

//    @RequestMapping(value = "/mans",method = RequestMethod.GET)
    public long getNumElemDB(){
        return service.getNumElemBD();
    }

}
