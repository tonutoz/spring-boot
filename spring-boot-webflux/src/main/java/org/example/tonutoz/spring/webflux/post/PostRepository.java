package org.example.tonutoz.spring.webflux.post;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Post,Long> {

  @Query("""
        SELECT *
        FROM POST p 
        WHERE p.title LIKE CONCAT('%', :keyword, '%') OR 
              p.content  LIKE CONCAT('%', :keyword, '%')
        """)
  public Flux<Post> searchByKeyWord(final String keyword);

}
