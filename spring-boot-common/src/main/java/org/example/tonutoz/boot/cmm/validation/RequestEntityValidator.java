package org.example.tonutoz.boot.cmm.validation;

import org.example.tonutoz.boot.cmm.exception.ErrorResponse.ValidationError;
import java.util.List;

/**
 * Request Entity 검증 인터페이스
 * @param <T>
 */
public interface RequestEntityValidator<T>{

  public List<ValidationError> validate(T request);

}
