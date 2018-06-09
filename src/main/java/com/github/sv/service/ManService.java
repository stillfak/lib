package com.github.sv.service;

import com.github.sv.models.Man;

import java.util.List;
import java.util.Optional;

public interface ManService {

    Man add(Man man);

    Man delete(Man man);

    Man deleteById(Long id);

    List<Man> find(String name);

    List<Man> findAll();

    Optional<Man> findById(Long id);
}
