package org.example.tonutoz.spring.webflux.post;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

  private final PostService postService;

  @GetMapping
  public Mono<ResponseEntity<List<PostResponse>>> getAllPost() {
    return postService.getAllPost().collectList().map(ResponseEntity::ok);
  }

  @GetMapping("/{postid}")
  public Mono<ResponseEntity<PostResponse>> getPost(@PathVariable final Long postid) {
    return postService.getPost(postid).map(ResponseEntity::ok);
  }

  @PostMapping
  public Mono<ResponseEntity<PostResponse>> createPost(@RequestBody final PostRequest postRequest) {
    return postService.writePost(postRequest).thenReturn(ResponseEntity.created(URI.create("/")).build());
  }
}