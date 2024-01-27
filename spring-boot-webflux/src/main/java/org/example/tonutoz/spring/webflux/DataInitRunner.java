package org.example.tonutoz.spring.webflux;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tonutoz.spring.webflux.post.Post;
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
//    var userFlux = userRepository.findAll();

//    for(int i=0;i<100;i++)
//      initPostData(userFlux);

  }
  private String generateRandomTitle() {
    Random random = new Random();
    String title = random.ints(48, 122) // ASCII value from '0' to 'z'
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // only '0'-'9', 'A'-'Z' and 'a'-'z'
        .limit(10) // length of the random string
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    System.out.println(title);
    return title;
  }

  private void initPostData(Flux<User> userFlux) {
    var random = new Random();

    userFlux.subscribe(user -> {

      log.info("user{}",user);
      var postList = IntStream.rangeClosed(1,random.nextInt(5,200)).mapToObj(i->
          Post.builder().title(generateRandomTitle()).content(user.getName()+"이 "+i+"번째로 글을 씁니다").authorId(user.getId()).build()
      ).toList();
      postRepository.saveAll(postList).subscribe(post -> log.info("Saved post: {}", post));

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
