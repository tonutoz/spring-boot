package org.example.tonutoz.spring.webflux.webflux.post;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.tonutoz.spring.webflux.webflux.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table("post")
@Builder
@Getter
@ToString
public class Post {

  @Id
  private Long id;

  private String title;

  private String content;

  private Long authorId;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Transient
  private User author;
}
