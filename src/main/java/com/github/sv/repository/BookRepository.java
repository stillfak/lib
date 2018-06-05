package com.github.sv.repository;

import com.github.sv.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByBookName(String bookName);

    Optional<Book> findById(Long id);

//    List<Book> findAll(int maxPageSize);
}
