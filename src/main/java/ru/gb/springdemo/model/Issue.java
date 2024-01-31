package ru.gb.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность выдача")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор")
  private Long id;

  @Column(name = "book_id")
  @Schema(description = "Идентификатор книги")
  private long bookId;

  @Column(name = "reader_id")
  @Schema(description = "Идентификатор читателя")
  private long readerId;

  @Column(name = "issued_at")
  @Schema(description = "Время открытия выдачи")
  private LocalDateTime issued_at;

  @Column(name = "returned_at")
  @Schema(description = "Время закрытия выдачи")
  private LocalDateTime returned_at;

  public Issue(long bookId, long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = LocalDateTime.now();
  }
}
