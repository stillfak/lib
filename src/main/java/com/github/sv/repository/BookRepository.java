package com.github.sv.repository;

import com.github.sv.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByBookName(String bookName);
}
