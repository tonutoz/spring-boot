package org.example.tonutoz.spring;

import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tonutoz.spring.message.Message;
import org.example.tonutoz.spring.message.MessageRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageInit implements ApplicationRunner {

  private final MessageRepository messageRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("zzzzzzzzzzzzzzzzzzzzzz");
    final int dataCount = 10000;
    List<Message> messageList = IntStream.range(0,dataCount).mapToObj((i) -> Message.builder().content("cotent" +i).build()).toList();

    messageRepository.saveAll(messageList);
  }
}
