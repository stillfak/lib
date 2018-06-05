package com.github.sv.service.impl;

import com.github.sv.service.BookService;
import com.github.sv.models.Book;
import com.github.sv.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;//= (BookRepository) Persistence.createEntityManagerFactory("").createEntityManager();

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book add(Book book) {
        return repository.save(book);
    }

    @Override
    public Book delete(Book book) {
        repository.delete(book);
        return book;
    }

    @Override
    public List<Book> find(String name) {
        return repository.findByBookName(name);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public BookRepository getRepository() {
        return repository;
    }

}
