package org.example.tonutoz.spring.webflux.webflux.post;

import reactor.core.publisher.Flux;

public interface PostCustomRepository {

  Flux<PostUserResult> findAllWithAuthor(final Long postId);

}
