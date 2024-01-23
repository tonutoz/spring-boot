package io.whatap.assignment.cmm.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public abstract class CommonDto {

  protected LocalDateTime createdOn;
  protected LocalDateTime modifiedOn;
}
