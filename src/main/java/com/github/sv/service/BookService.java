package com.github.sv.service;

import com.github.sv.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book add(Book book);

    void deleteById(Long id);

    List<Book> find(String name);

    Page<Book> findAll(Pageable pageable);

    Book findById(Long id);
}
