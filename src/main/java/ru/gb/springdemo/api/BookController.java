package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

@RestController
@RequestMapping("/book")
@Tag(name="book controller", description="контролер для работы с книгами")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Показать книгу",
            description = "Выводит книгу с указанным ID"
    )
    @Secured("ROLE_USER")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить книгу",
            description = "удаляет книгу с ID из БД"
    )
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping
    @Operation(
            summary = "Создать книгу",
            description = "Вносит в БД новую книгу с названием: name"
    )
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
}
