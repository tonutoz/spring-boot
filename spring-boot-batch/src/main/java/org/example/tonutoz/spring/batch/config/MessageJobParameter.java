package org.example.tonutoz.spring.batch.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@JobScope
@Component
public class MessageJobParameter {

  private LocalDate date;

  @Value("${chunk-size:1000}")
  private int chunkSize;

  @Value("${batch.date:2024-01-25}")
  public void setDate(String date) {
    this.date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
  }
}
