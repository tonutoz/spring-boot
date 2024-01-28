package org.example.tonutoz.spring.webflux.webflux.user;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import org.example.tonutoz.spring.webflux.webflux.post.PostResponse;

@Builder
public record UserReponse(
    Long id,
    String name,
    Integer age,
    LocalDateTime createdAt,
    List<PostResponse> posts
) {

  public static UserReponse from(final User user) {

    List<PostResponse> posts = user.getPosts().stream().map(PostResponse::from).toList();

    return UserReponse.builder()
        .id(user.getId())
        .name(user.getName())
        .age(user.getAge())
        .createdAt(user.getCreatedAt())
        .posts(posts)
        .build();
  }

}
