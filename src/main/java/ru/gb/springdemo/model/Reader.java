package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  private final long id;
  private final String name;
//  @Value("${reader.maxBookCount}")
  private int maxBookCount;

  public Reader(String name) {
    this(sequence++, name);
  }

}
