package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.IssueRequest;
import ru.gb.springdemo.exceptions.ReaderMaxAllowedBookException;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name="issue controller", description="контролер для работы с выдачами книг")
public class IssuerController {

  @Autowired
  private IssuerService service;

  @PostMapping("/{issueID}")
  @Operation(
          summary = "Закрыть выдачу",
          description = "Позволяет закрыть ранее открытую выдачу книги"
  )
  public ResponseEntity<Issue> closeIssue(@PathVariable Long issueID) {
    Issue issue;
    try {
      issue = service.closeIssue(issueID);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag(e.getMessage()).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

  @GetMapping("/{id}")
  @Operation(
          summary = "Выдать запись выдачи",
          description = "Выдает запись в БД по выдаче под номером :ID"
  )
  public Issue getIssue(@PathVariable Long id) {
    return service.getIssueById(id);
  }

  @PostMapping
  @Operation(
          summary = "Оформить выдачу",
          description = "Делает запись о выдаче книги с bookId читателю с readerId"
  )
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag(e.getMessage()).build();
    } catch (ReaderMaxAllowedBookException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).eTag(e.getMessage()).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

}
