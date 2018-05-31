package com.github.sv.controller;

import com.github.sv.dto.ManDTO;
import com.github.sv.models.Man;
import com.github.sv.service.impl.ManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lib/libMans")
public class ManLibRestController {

    private final ManServiceImpl service;

    @Autowired
    public ManLibRestController(ManServiceImpl service) {
        this.service = service;
    }

//    @RequestMapping(value = "/mans", method = RequestMethod.GET)
//    public List<ManDTO> getAllMan(SpringDataWebProperties.Pageable pageable) {
//        return (List<ManDTO>) service.getRepository().findAll();
//    }

//    @RequestMapping(value = "/mans/{id}", method = RequestMethod.GET)
//    public ManDTO get(@PathVariable Long id) {
//        return (ManDTO) service.findById(id).get();
//    }
    @RequestMapping(value = "/mans", method = RequestMethod.POST)
    public Man add(String lastName){
        return service.add(new Man(lastName));
    }

    @RequestMapping(value = "/mans/{id}",method = RequestMethod.PUT)
    public String edit(@PathVariable Long id){
        return "";
    }
    @RequestMapping(value = "mans/{id}",method = RequestMethod.DELETE)
    public Man delete(@PathVariable Long id){
        return service.delete(service.findById(id).get());
    }
}
