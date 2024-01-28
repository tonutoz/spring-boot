package org.example.tonutoz.spring.webflux.webflux.user;

import reactor.core.publisher.Flux;

public interface UserCostomRepository {
  Flux<User> findAllWithPosts(Long userId);

}
