package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Book getBook(Long id) {
        return bookRepository.getBookById(id);
    }

    public boolean deleteBook(Long id) {
        return bookRepository.deleteBook(id);
    }

    public boolean createBook(Book book) {
        return bookRepository.createBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
