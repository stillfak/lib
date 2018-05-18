package com.github.sv.repository;


import com.github.sv.models.Man;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ManRepository extends CrudRepository<Man, Long> {

    List<Man> findByLastName(String lastName);
}
