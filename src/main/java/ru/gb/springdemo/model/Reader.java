package ru.gb.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@NoArgsConstructor
@Table(name = "readers")
@Schema(description = "Сущность читатель")
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор")
  private Long id;

  @Column(name = "user_name")
  @Schema(description = "Имя читателя", example = "Игорь")
  private String name;

  @Column(name = "max_book_count")
  @Value("${reader.maxBookCount}")
  @Schema(description = "Максимальное число выдачей", example = "5")
  private Integer maxBookCount;

}
