package org.example.tonutoz.spring.webflux.post;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
public record PostResponse(
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public static PostResponse from(final Post post) {
    return PostResponse.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .authorId(post.getAuthorId())
        .createdAt(post.getCreatedAt())
        .updatedAt(post.getUpdatedAt())
        .build();
  }
}
