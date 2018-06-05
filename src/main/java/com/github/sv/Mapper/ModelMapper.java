package com.github.sv.Mapper;

import com.github.sv.dto.BookDTO;
import com.github.sv.dto.ManDTO;
import com.github.sv.models.Book;
import com.github.sv.models.Man;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapper {

    private org.modelmapper.ModelMapper modelMapper;

    public ModelMapper() {
        this.modelMapper = new org.modelmapper.ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public ManDTO convertToDto(Man man) {
        return modelMapper.map(man, ManDTO.class);
    }

    public Man convertToEnable(ManDTO sourceObject) {
        return modelMapper.map(sourceObject, Man.class);
    }
    public BookDTO convertToDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book convertToEnable(BookDTO sourceObject) {
        return modelMapper.map(sourceObject, Book.class);
    }
}
