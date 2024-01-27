package org.example.tonutoz.spring.webflux.post;

import lombok.Builder;

@Builder
public record PostRequest(
     String title,
     String content,
     Long authorId
) {
  public Post toEntity() {
    return Post.builder()
        .title(title)
        .content(content)
        .authorId(authorId)
        .build();
  }

}
