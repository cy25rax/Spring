package ru.gb.springdemo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueDetails {
    private final String bookName;
    private final String readerName;
    private final LocalDateTime issued_at;
    private final LocalDateTime returned_at;

}
//* 1.3 /ui/issues - (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
