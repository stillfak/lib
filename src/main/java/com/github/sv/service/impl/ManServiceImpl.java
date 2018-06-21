package com.github.sv.service.impl;

import com.github.sv.exception.NotFoundException;
import com.github.sv.models.Man;
import com.github.sv.service.ManService;
import com.github.sv.repository.ManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManServiceImpl implements ManService {

    private final ManRepository repository;

    @Autowired
    public ManServiceImpl(ManRepository repository) {
        this.repository = repository;
    }

    @Override
    public Man add(Man man) {
        return repository.save(man);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Man> find(String name) {
        return repository.findByLastName(name);
    }

    @Override
    public Page<Man> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Man findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(404));
    }

    public long count() {
        return repository.count();
    }

    public Man update(Man man) {
        return repository.save(man);
    }
}
