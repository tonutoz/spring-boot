package org.example.tonutoz.spring.webflux;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tonutoz.spring.webflux.post.PostRepository;
import org.example.tonutoz.spring.webflux.user.User;
import org.example.tonutoz.spring.webflux.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitRunner implements ApplicationRunner {

  private final UserRepository userRepository;

  private final PostRepository postRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    //userInit();
    var userlist = userRepository.findAll();

    userlist.subscribe(user -> {
      log.info("user{}",user);
    }) ;


  }



  private void userInit() {
    log.info("userInit ");
    var ids= "tonuto,tonutoz,nice,amen,ame";
    var random = new Random();
    var users = Arrays.stream(ids.split(",")).
        map(s-> User.builder()
            .name(s)
            .age((random.nextInt(29,55))).build()).peek(System.out::println).toList();
    userRepository.saveAll(users)
        .subscribe(user -> log.info("Saved user: {}", user));
  }

}
