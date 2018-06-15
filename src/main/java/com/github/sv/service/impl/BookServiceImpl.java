package com.github.sv.service.impl;

import com.github.sv.models.Book;
import com.github.sv.repository.BookRepository;
import com.github.sv.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Book deleteById(Long id) {
        Book book = repository.findById(id).get();
        repository.deleteById(id);
        return book;
    }


    @Override
    public List<Book> find(String name) {
        return repository.findByBookName(name);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).get();
    }

    public long getRepositoryCount() {
        return repository.count();
    }


    public Book update(Book book) {
        return repository.save(book);
    }

}