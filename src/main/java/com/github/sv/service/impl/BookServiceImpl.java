package com.github.sv.service.impl;

import com.github.sv.exception.NotFoundException;
import com.github.sv.models.Book;
import com.github.sv.repository.BookRepository;
import com.github.sv.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    @Override
    public List<Book> find(String name) {
        return repository.findByBookName(name);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(404));
    }

    public long count() {
        return repository.count();
    }


    public Book update(Book book) {
        return repository.save(book);
    }

}