package org.example.tonutoz.spring.webflux.webflux.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Mono<List<UserReponse>> getUserPost(final Long userId) {
    return userRepository.findAllWithPosts(userId).map(UserReponse::from).collectList();
  }

}
