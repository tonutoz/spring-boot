package org.example.tonutoz.spring.webflux.webflux.post;

import java.time.LocalDateTime;

public record PostUserResult(
    Long postId,
    String title,
    String content,
    LocalDateTime postCreatedAt,
    Long authorId,
    String authorName,
    Integer authorAge) {

}
