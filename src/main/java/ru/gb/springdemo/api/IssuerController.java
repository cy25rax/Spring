package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.exceptions.ReaderMaxAllowedBookException;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

  @Autowired
  private IssuerService service;

  @PostMapping("/{issueID}")
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
  public Issue getIssue(@PathVariable Long id) {
    return service.getIssueById(id);
  }

  @PostMapping
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
