package com.github.sv.service.impl;

import com.github.sv.models.Man;
import com.github.sv.service.ManService;
import com.github.sv.repository.ManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManServiceImpl implements ManService {

    private final ManRepository repository;

    @Autowired
    public ManServiceImpl(ManRepository repository) {
        this.repository = repository;
    }

    @Override
    public Man add(Man man) {
        repository.save(man);
        return man;
    }

    @Override
    public Man delete(Man man) {
        repository.delete(man);
        return man;
    }

    @Override
    public List<Man> find(String name) {
        return repository.findByLastName(name);
    }

    @Override
    public Optional<Man> findById(Long id) {
        return repository.findById(id);
    }

    public ManRepository getRepository() {
        return repository;
    }
}
