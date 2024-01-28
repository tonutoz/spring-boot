package org.example.tonutoz.spring.webflux.webflux;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tonutoz.spring.webflux.webflux.post.Post;
import org.example.tonutoz.spring.webflux.webflux.post.PostRepository;
import org.example.tonutoz.spring.webflux.webflux.user.User;
import org.example.tonutoz.spring.webflux.webflux.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import reactor.core.publisher.Flux;

@Slf4j
//@Component
@RequiredArgsConstructor
public class DataInitRunner implements ApplicationRunner {

  private final UserRepository userRepository;

  private final PostRepository postRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    //userInit();
    var userFlux = userRepository.findAll();

     for(int i=0;i<100;i++)
      initPostData(userFlux);

  }
  private String generateRandomTitle() {
    Random random = new Random();
    var titles = new String[]{"일기", "글써봅니다", "다들봐라", "이거이상한데", "뭐지", "오랫만에 왔습니다."};
    return titles[random.nextInt(titles.length)];
  }

  private String generateRandomContent() {
    var contents = new String[]{"""
    잘못된 내용이 있어서 정정합니다.
    다시쓸게요
    """, """
    글써봅니다 내용이좀 있어서요.
    하이하이하이
    """, """
    다들봐라 이건좀 아니지 않냐
    뭔데뭔데뭔데
    """, """
    이거이상한데
    한번 봐주세요 stack over flow
    """, """
    잘못된 다른 테스트 코드 : 다른 테스트 코드에 오류가 있는 경우 @SpringBootTest 어노테이션을 사용한 테스트가 실패할 수 있습니다.
    다른 테스트 코드를 확인해 보세요.
    """, """
    위 방법을 모두 시도해보았는데도 문제가 해결되지 않는다면, 자세한 오류 메세지 또는 스택 추적 결과를
    제공해주시면 좀 더 도움을 드릴 수 있습니다.
    """};
    var random = new Random();
    int randomIndex = random.nextInt(contents.length);
    return contents[randomIndex];
  }

  private void initPostData(Flux<User> userFlux) {
    var random = new Random();
    var localdate = LocalDate.now();
    userFlux.subscribe(user -> {

      log.info("user{}",user);
      var postList = IntStream.rangeClosed(1,random.nextInt(100,200)).mapToObj(i->{
        return Post.builder().title(generateRandomTitle()).content(
            localdate + " " + user.getName() + "이 " + "번째로 글을 씁니다 내용은 " + generateRandomContent()).authorId(user.getId()).build();
      }).toList();
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
