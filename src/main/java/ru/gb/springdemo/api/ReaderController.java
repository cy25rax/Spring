package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
@Tag(name="reader controller", description="контролер для работы с читателями")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/{id}/issue")
    @Operation(
            summary = "Показать все выдачи читателя",
            description = "Показывает все выдачи читателя с ID"
    )
    public List<Issue> getAllIssueByReaderId(@PathVariable Long id) {
        return readerService.getAllIssueByReaderId(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Показать читателя",
            description = "Выводит читателя с ID"
    )
    public Reader getReader(@PathVariable Long id) {
        return readerService.getReader(id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить читателя",
            description = "Удаляет читателя с ID"
    )
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }

    @PostMapping
    @Operation(
            summary = "Создать читателя",
            description = "Создает читателя с именем: name"
    )
    public Reader createReader(@RequestBody Reader reader) {
        return readerService.createReader(reader);
    }
}
