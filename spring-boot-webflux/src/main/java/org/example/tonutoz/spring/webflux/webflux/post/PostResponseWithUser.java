package org.example.tonutoz.spring.webflux.webflux.post;

import java.time.LocalDateTime;
import lombok.Builder;
@Builder
public record PostResponseWithUser(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    Long authorId,
    String authorName,
    Integer authorAge
) {

}
