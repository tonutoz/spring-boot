package org.example.tonutoz.boot.cmm.validation;

import java.util.List;
import org.example.tonutoz.boot.cmm.exception.ErrorResponse.ValidationError;

/**
 * Request Entity 검증 인터페이스
 * @param <T>
 */
public interface RequestEntityValidator<T>{

  List<ValidationError> validate(T request);

}
