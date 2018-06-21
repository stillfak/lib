package com.github.sv.service;

import com.github.sv.models.Man;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManService {

    Man add(Man man);

    void deleteById(Long id);

    List<Man> find(String name);

    Page<Man> findAll(Pageable pageable);

    Man findById(Long id);
}
