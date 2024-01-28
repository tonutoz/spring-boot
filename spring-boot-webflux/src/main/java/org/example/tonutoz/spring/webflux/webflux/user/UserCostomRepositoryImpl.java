package org.example.tonutoz.spring.webflux.webflux.user;

import io.r2dbc.spi.Row;
import java.time.LocalDateTime;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.example.tonutoz.spring.webflux.webflux.post.Post;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class UserCostomRepositoryImpl implements UserCostomRepository {

  private static final String USER_ID_FIELD_NAME = "userId";
  private final DatabaseClient databaseClient;

  public Flux<User> findAllWithPosts(final Long userId) {
    var sqlWithPost = """
        SELECT 
            u.id as userId, 
            u.name as userName, 
            u.age as userAge, 
            u.created_at as userCreatedAt, 
            u.updated_at as userUpdatedAt,
            p.id as postId, 
            p.title as postTitle, 
            p.content as postContent, 
            p.created_at as postCreatedAt, 
            p.updated_at as postUpdatedAt
        FROM user u
        INNER JOIN post p
        ON u.id = p.author_id
        WHERE u.id = :userId
        """;
    return databaseClient.sql(sqlWithPost)
        .bind("userId", userId)
        .map((row, metadata) -> {
          Post post = this.extractPostDataFromRow(row);
          return this.buildUserFromRowAndPost(row, post);
        }).all();
  }

  private Post extractPostDataFromRow(Row row) {
    return Post.builder()
        .id(row.get("postId", Long.class))
        .title(row.get("postTitle", String.class))
        .content(row.get("postContent", String.class))
        .createdAt(row.get("postCreatedAt", LocalDateTime.class))
        .updatedAt(row.get("postUpdatedAt", LocalDateTime.class))
        .build();
  }

  private User buildUserFromRowAndPost(Row row, Post post) {
    return User.builder()
        .id(row.get("userId", Long.class))
        .name(row.get("userName", String.class))
        .age(row.get("userAge", Integer.class))
        .createdAt(row.get("userCreatedAt", LocalDateTime.class))
        .updatedAt(row.get("userUpdatedAt", LocalDateTime.class))
        .posts(Collections.singletonList(post))
        .build();
  }
}
