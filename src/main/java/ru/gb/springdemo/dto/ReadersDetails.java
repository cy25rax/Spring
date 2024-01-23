package ru.gb.springdemo.dto;

import lombok.Data;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;

@Data
public class ReadersDetails {
    private final String bookName;
    private final LocalDateTime issued_at;

    public ReadersDetails(Book book, Issue issue) {
        this.bookName = book.getName();
        this.issued_at = issue.getIssued_at();
    }
}
