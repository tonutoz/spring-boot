package org.example.tonutoz.spring.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessageJobConfig {

  public static final String JOB_NAME = "MESSAGE_JOB";

  private final Step messageStep;

  public MessageJobConfig(
      @Qualifier(MessageStepConfig.STEP_NAME) Step messageStep) {
    this.messageStep = messageStep;
  }

  @Bean(JOB_NAME)
  public Job messageJob(JobRepository jobRepository) {
    return new JobBuilder(JOB_NAME, jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(messageStep)
        .build();
  }

}
