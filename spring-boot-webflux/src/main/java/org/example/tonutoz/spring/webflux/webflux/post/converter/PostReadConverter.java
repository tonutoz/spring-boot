package org.example.tonutoz.spring.webflux.webflux.post.converter;

import io.r2dbc.spi.Row;
import java.time.LocalDateTime;
import org.example.tonutoz.spring.webflux.webflux.post.Post;
import org.example.tonutoz.spring.webflux.webflux.user.User;
import org.springframework.core.convert.converter.Converter;

public class PostReadConverter implements Converter<Row, Post> {

  @Override
  public Post convert(Row source) {
    var user = User.builder()
        .id((Long) source.get("authorId"))
        .name((String) source.get("authorName"))
        .age((Integer) source.get("authorAge"))
        .build();
    return Post.builder()
        .id((Long) source.get("postId"))
        .title((String) source.get("postTitle"))
        .content((String) source.get("postContent"))
        .createdAt((LocalDateTime) source.get("postCreatedAt"))
        .authorId(((Long) source.get("authorId")))
        .author(user)
        .build();
  }
}
