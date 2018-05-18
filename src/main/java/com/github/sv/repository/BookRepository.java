package com.github.sv.repository;

import com.github.sv.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByBookName(String bookName);
    Optional<Book> findById(Long id);
}
