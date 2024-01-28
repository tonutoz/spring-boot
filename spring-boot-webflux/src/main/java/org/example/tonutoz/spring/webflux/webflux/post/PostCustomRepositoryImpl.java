package org.example.tonutoz.spring.webflux.webflux.post;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository{
  private final DatabaseClient databaseClient;

  @Override
  public Flux<PostUserResult> findAllWithAuthor(final Long postId) {
    var sql = """
        SELECT p.id AS postId, 
               p.title AS postTitle, 
               p.content, 
               p.created_at AS postCreatedAt, 
               u.id AS authorId,
               u.name AS authorName, 
               u.age AS authorAge
        FROM post p 
        INNER JOIN user u 
        ON p.author_id = u.id
        WHERe p.id = :postId
        """;

    return databaseClient
        .sql(sql)
        .bind("postId",postId)
        .map(row -> new PostUserResult(
            row.get("postId", Long.class),
            row.get("title", String.class),
            row.get("content", String.class),
            row.get("postCreatedAt", LocalDateTime.class),
            row.get("authorId", Long.class),
            row.get("authorName", String.class),
            row.get("authorAge", Integer.class))).all();
  }
}
