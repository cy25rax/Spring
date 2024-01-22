package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

  public Issue getIssueById(Long id) {
    return issues.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElse(null);
  }

  public List<Issue> getAllIssueByReaderId(Long id) {
    return issues.stream()
            .filter(it -> Objects.equals(it.getReaderId(), id) && it.getReturned_at() == null)
            .collect(Collectors.toList());
  }

  public Issue closeIssue(Long issueID) {
    return issues.stream()
            .filter(it -> Objects.equals(it.getId(), issueID) && it.getReturned_at() == null)
            .peek(it -> it.setReturned_at(LocalDateTime.now()))
            .findFirst()
            .orElse(null);
  }

    public List<Issue> getAllIssues() {
      return List.copyOf(issues);
    }
}
