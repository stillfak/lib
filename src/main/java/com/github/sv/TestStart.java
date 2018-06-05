package com.github.sv;

import com.github.sv.models.Book;
import com.github.sv.models.Man;
import com.github.sv.repository.BookRepository;
import com.github.sv.repository.ManRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestStart {

    private static final Logger log = LoggerFactory.getLogger(TestStart.class);

    public static void main(String[] args) {
        SpringApplication.run(TestStart.class);
    }

    @Bean
    public CommandLineRunner init(ManRepository manRepository, BookRepository bookRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                bookRepository.save(new Book("book" + i, (long) 500 + i, "author" + i));
            }
            for (int i = 0; i < 10; i++) {
                manRepository.save(new Man("Man" + i));

            }
        };
    }
}
