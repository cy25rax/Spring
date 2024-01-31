package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.dto.IssueRequest;
import ru.gb.springdemo.dto.IssueDetails;
import ru.gb.springdemo.exceptions.ReaderMaxAllowedBookException;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    if (bookRepository.getReferenceById(request.getBookId()) == null) {
      throw new NoSuchElementException("Book not found with id: \"" + request.getBookId() + "\"");
    }
    Reader reader = readerRepository.getReferenceById(request.getReaderId());
    if (reader == null) {
      throw new NoSuchElementException("Reader not found with id: \"" + request.getReaderId() + "\"");
    }
    reader.setMaxBookCount(reader.getMaxBookCount() - 1);
    if (reader.getMaxBookCount() < 0) {
      reader.setMaxBookCount(reader.getMaxBookCount() + 1);
      throw new ReaderMaxAllowedBookException("Reader have max book count \"" + request.getReaderId() + "\"");
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  public Issue getIssueById(Long id) {
    return issueRepository.getReferenceById(id);
  }

  public Issue closeIssue(Long issueID) {
    Issue issue = issueRepository.getReferenceById(issueID);
    if (issue == null) {
      throw new NoSuchElementException("Issue not found with id: \"" + issueID + "\"");
    }
    Reader reader = readerRepository.getReferenceById(issue.getReaderId());
    reader.setMaxBookCount(reader.getMaxBookCount() + 1);
    issue.setReturned_at(LocalDateTime.now());
    issueRepository.save(issue);
    return issue;
  }

    public List<Issue> getAllIssues() {
      return issueRepository.findAll();
    }

    public List<IssueDetails> getAllIssuesDetail() {
      return getAllIssues().stream()
              .map(it -> new IssueDetails(
                      bookRepository.getReferenceById(it.getBookId()).getName(),
                      readerRepository.getReferenceById(it.getReaderId()).getName(),
                      it.getIssued_at(),
                      it.getReturned_at()
              ))
              .collect(Collectors.toList());
    }
}
