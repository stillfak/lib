package com.github.sv.service;

import com.github.sv.models.Man;

import java.util.List;
import java.util.Optional;

public interface ManService {

    Man add(Man man);

    Man delete(Man man);

    List<Man> find(String name);

    Optional<Man> findById(Long id);
}
