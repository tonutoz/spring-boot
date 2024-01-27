package org.example.tonutoz.spring.webflux.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public Flux<PostResponse> getAllPost() {
    return postRepository.findAll().map(PostResponse::from);
  }

  public Mono<PostResponse> getPost(final Long id) {
    return postRepository.findById(id).map(PostResponse::from);
  }

  public Mono<PostResponse> writePost(final PostRequest postRequest) {
    return postRepository.save(postRequest.toEntity()).map(PostResponse::from);
  }

  public Flux<PostResponse> getKeywordPost(final String keyword) {
    return postRepository.searchByKeyWord(keyword).map(PostResponse::from);
  }

}
