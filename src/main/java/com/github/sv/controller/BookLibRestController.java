package com.github.sv.controller;

import com.github.sv.dto.BookDTO;
import com.github.sv.exception.NotFoundException;
import com.github.sv.mapper.ModelMapper;
import com.github.sv.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * В классе реализованы базовые методы взаимодействия
 * Админестратора с БД
 *
 * @author Gavrikov V. 15It18
 */
@RestController
@RequestMapping(value = "lib/books")
public class BookLibRestController {

    private final BookServiceImpl service;
    /**
     * конвертирует обьект DTO в сущность БД и обратно
     */
    private final ModelMapper mapper;


    @Autowired
    public BookLibRestController(BookServiceImpl service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Дает доступ к просмотру страницы со списком книг
     *
     * @param pageable  конфигурирует возвращаемый результат (нномер страницы и размер страницы)
     * @return страница содержащая объекты BookDTO
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return service.findAll(pageable)
                .map(mapper::convertToDto);
    }

    /**
     * Дает доступ к просмотру одной книги.
     *
     * @param id уникальный идентификатор
     * @return книга в виде объекта передачи данных(DTO - data transfer object)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookDTO getBook(@PathVariable Long id) {
        return mapper.convertToDto(service.findById(id));
    }

    /**
     * Добавляет с базу данных новую книгу
     *
     * @param newBook книга c id = null
     * @return книга с добавленная в БД с id != null
     */

    @RequestMapping(method = RequestMethod.POST)
    public BookDTO add(@RequestBody BookDTO newBook) {
        return mapper.convertToDto(service.addOrUpdate(mapper.convertToEnable(newBook)));
    }

    /**
     * обновит информацию о книге
     *
     * @param id  уникальный идентификатор
     * @param editBook обновленная информация о книге
     * @return обновленная информациия  о книге добавленная в бд
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BookDTO edit(@PathVariable Long id,
                        @RequestBody BookDTO editBook) {
        editBook.setId(id);
        return mapper.convertToDto(service.addOrUpdate(mapper.convertToEnable(editBook)));
    }

    /**
     * Удалит книгу из БД
     *
     * @param id уникальный идентификатор
     * @return результат удаления "успешный" или "ошибка"
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return "Удален " + id;
        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
            return "Не удален " + id + " " + new NotFoundException(404).getMessage();
        }
    }

    /**
     * Колличество сущьностей в БД
     * @return колличество сущьностей в БД
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long count() {
        return service.count();
    }
}