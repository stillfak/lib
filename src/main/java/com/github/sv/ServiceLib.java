package com.github.sv;

import com.github.sv.models.Book;
import com.github.sv.models.Man;
import com.github.sv.repository.BookRepository;
import com.github.sv.repository.ManRepository;

import java.util.List;
import java.util.Optional;

public class ServiceLib {

    public static void delete(BookRepository repository, Book book) {
        repository.delete(book);
    }

    public static void delete(ManRepository repository, Man man) {
        repository.delete(man);
    }

    public static void add(BookRepository repository, Book book) {
        repository.save(book);
    }

    public static void add(ManRepository repository, Man man) {
        repository.save(man);
    }

    public static List<Man> find(ManRepository repository, String lastName) {
        return repository.findByLastName(lastName);
    }

    public static List<Book> find(BookRepository repository, String bookName) {
        return repository.findByBookName(bookName);
    }

    public static Optional<Man> findById(ManRepository repository, Long id) {
        return repository.findById(id);
    }

    public static Optional<Book> findById(BookRepository repository, Long id) {
        return repository.findById(id);
    }

}
