package com.github.sv.mapper;

import com.github.sv.dto.BookDTO;
import com.github.sv.dto.ManDTO;
import com.github.sv.models.Book;
import com.github.sv.models.Man;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ModelMapper {


    public ModelMapper() {
    }

    public ManDTO convertToDto(Man man) {
        if (man.getBooksOnHand() == null) {
            return new ManDTO(
                    man.getLastName(),
                    null,
                    man.getId());
        } else if (man.getId() == null) {
            return new ManDTO(
                    man.getLastName(),
                    man.getBooksOnHand().
                            stream().
                            map(this::convertToDto).
                            collect(Collectors.toList()),
                    null
            );
        } else if (man.getId() == null && man.getBooksOnHand() == null) {
            return new ManDTO(
                    man.getLastName(),
                    null,
                    null
            );
        }

        return new ManDTO(
                man.getLastName(),
                man.getBooksOnHand().
                        stream().
                        map(this::convertToDto).
                        collect(Collectors.toList()),
                man.getId());
    }

    public Man convertToEnable(ManDTO sourceObject) {

        if (sourceObject.getBooksOnHand() == null) {
            return new Man(
                    sourceObject.getLastName(),
                    null,
                    sourceObject.getId());

        } else if (sourceObject.getId() == null) {
            return new Man(
                    sourceObject.getLastName(),
                    sourceObject.
                            getBooksOnHand().
                            stream().
                            map(this::convertToEnable).
                            collect(Collectors.toList()),
                    null);
        } else if (sourceObject.getId() == null && sourceObject.getBooksOnHand() == null) {
            return new Man(
                    sourceObject.getLastName(),
                    null,
                    null
            );
        }
        return new Man(
                sourceObject.getLastName(),
                sourceObject.
                        getBooksOnHand().
                        stream().
                        map(this::convertToEnable).
                        collect(Collectors.toList()),
                sourceObject.getId());
    }


    public BookDTO convertToDto(Book book) {
        if (book.getMan() == null) {
            return new BookDTO(
                    book.getBookName(),
                    book.getNumberOfPages(),
                    book.getAuthorBook(),
                    book.getId(),
                    null);


        } else if (book.getId() == null) {
            return new BookDTO(
                    book.getBookName(),
                    book.getNumberOfPages(),
                    book.getAuthorBook(),
                    null,
                    null);
        }
        return new BookDTO(
                book.getBookName(),
                book.getNumberOfPages(),
                book.getAuthorBook(),
                book.getId(),
                convertToDto(book.getMan()));
    }

    public Book convertToEnable(BookDTO bookDTO) {
        if (bookDTO.getManDTO() == null) {
            return new Book(
                    bookDTO.getBookName(),
                    bookDTO.getNumberOfPages(),
                    bookDTO.getAuthorBook(),
                    bookDTO.getId(),
                    null

            );
        } else if (bookDTO.getId() == null) {
            return new Book(
                    bookDTO.getBookName(),
                    bookDTO.getNumberOfPages(),
                    bookDTO.getAuthorBook(),
                    null,
                    null);
        }
        return new Book(
                bookDTO.getBookName(),
                bookDTO.getNumberOfPages(),
                bookDTO.getAuthorBook(),
                bookDTO.getId(),
                convertToEnable(bookDTO.getManDTO()));

    }
}