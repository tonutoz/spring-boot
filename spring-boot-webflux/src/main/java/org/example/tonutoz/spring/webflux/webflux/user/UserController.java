package org.example.tonutoz.spring.webflux.webflux.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}/post")
  public Mono<ResponseEntity<List<UserReponse>>> getUserPosts(@PathVariable Long userId) {
      return userService.getUserPost(userId).map(ResponseEntity::ok);
  }

}
