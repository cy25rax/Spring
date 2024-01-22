package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/{id}/issue")
    public List<Issue> getAllIssueByReaderId(@PathVariable Long id) {
        return readerService.getAllIssueByReaderId(id);
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable Long id) {
        return readerService.getReader(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteReader(@PathVariable Long id) {
        return readerService.deleteReader(id);
    }

    @PostMapping
    public boolean createReader(@RequestBody Reader reader) {
        return readerService.createReader(reader);
    }
}
