package com.github.sv.repository;


import com.github.sv.models.Man;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ManRepository extends JpaRepository<Man, Long> {

    List<Man> findByLastName(String lastName);
}
