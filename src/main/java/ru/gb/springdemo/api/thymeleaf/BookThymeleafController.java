package ru.gb.springdemo.api.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.service.BookService;

@Controller
@RequestMapping("/ui/books")
public class BookThymeleafController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

}
