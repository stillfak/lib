package com.github.sv.service;

import com.github.sv.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book add(Book book);

    Book delete(Book book);

    List<Book> find(String name);

    Optional<Book> findById(Long id);
}
