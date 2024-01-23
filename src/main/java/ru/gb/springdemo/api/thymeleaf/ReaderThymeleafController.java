package ru.gb.springdemo.api.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.service.ReaderService;

@Controller
@RequestMapping("/ui/readers")
public class ReaderThymeleafController {
    @Autowired
    private ReaderService readerService;

    @GetMapping()
    public String getAllReaders(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "readers";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model, @PathVariable Long id) {
        model.addAttribute("reader", readerService.getReader(id));
        model.addAttribute("issues", readerService.getAllBooksNames(id));
        return "details";
    }

}
