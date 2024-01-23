package io.whatap.assignment.cmm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class CommonEntity {

  @Column(name = "created_on", nullable = false, updatable = false)
  @Comment("생성 일자")
  @CreatedDate
  protected LocalDateTime createdOn;

  @Column(name = "modified_on")
  @Comment("수정 일자")
  @LastModifiedDate
  protected LocalDateTime modifiedOn;


}
