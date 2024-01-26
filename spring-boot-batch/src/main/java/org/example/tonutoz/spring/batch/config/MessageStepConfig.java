package org.example.tonutoz.spring.batch.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tonutoz.spring.message.Message;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class MessageStepConfig {
  public static final String STEP_NAME = "messageStep";

  private final EntityManagerFactory entityManagerFactory;
  private final MessageJobParameter jobParameter;

  @Bean(STEP_NAME)
  @JobScope
  public Step messageStep(
      JobRepository jobRepository, PlatformTransactionManager batchTransactionManager) {
    return new StepBuilder(STEP_NAME, jobRepository)
        .<Message, Message>chunk(jobParameter.getChunkSize(), batchTransactionManager)
        .reader(messageItemReader())
        .writer(messageItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public ItemStreamReader<Message> messageItemReader() {
    return new JpaPagingItemReaderBuilder<Message>()
        .name("messageItemReader")
        .entityManagerFactory(entityManagerFactory)
        .pageSize(jobParameter.getChunkSize())
        .queryString("SELECT m FROM Message m")
        .build();
  }

  @Bean
  public ItemWriter<Message> messageItemWriter() {
    return messages -> {
      for (Message message : messages) {
        log.info("message {}",message);
      }
    };
  }
}
