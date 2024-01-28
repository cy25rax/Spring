package ru.gb.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "issues")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "book_id")
  private long bookId;

  @Column(name = "reader_id")
  private long readerId;

  @Column(name = "issued_at")
  private LocalDateTime issued_at;

  @Column(name = "returned_at")
  private LocalDateTime returned_at;

  public Issue(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = LocalDateTime.now();
  }
}
