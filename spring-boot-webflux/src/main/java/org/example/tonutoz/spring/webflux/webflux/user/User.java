package org.example.tonutoz.spring.webflux.webflux.user;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.tonutoz.spring.webflux.webflux.post.Post;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
@Builder
@Getter
@ToString
public class User {

  @Id
  private Long id;

  private String name;

  private Integer age;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Transient
  private List<Post> posts;


}
