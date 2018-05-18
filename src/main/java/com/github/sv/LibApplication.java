package com.github.sv;

import com.github.sv.repository.BookRepository;
import com.github.sv.repository.ManRepository;
import com.github.sv.models.Book;
import com.github.sv.models.Man;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication
public class LibApplication {

    private static final Logger log = LoggerFactory.getLogger(LibApplication.class);

    public static void main(String[] args) {
//        try{
        SpringApplication.run(LibApplication.class);

//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Bean
    public CommandLineRunner ManRepository(ManRepository repository, BookRepository bookRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                bookRepository.save(new Book("book"+i,(long) 600+i,"author"+i));
            }
            bookRepository.findAll().forEach(book -> {
                repository.save(new Man("Man"+ book.getId()));
            });
            ArrayList<Book> id = new ArrayList<>();
            bookRepository.findAll().forEach(book -> {
                id.add(book);
            });

//            repository.save(new Man("Man1"));

            repository.findByLastName("Man5").forEach(man -> {
                man.setBooksOnHand(id);
                log.info(man.toString());
            });


        };
    }
//    @Bean
//    public CommandLineRunner BookRepository(BookRepository repository){
//        return args -> {
//            repository.save(new Book("book",(long) 500, "author1"));
//
//            repository.findByBookName("book").forEach(book -> {
//                log.info(book.toString());
//            });
//        };
//    }
}
