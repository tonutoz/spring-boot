package io.whatap.assignment.cmm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorType {

  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "ERR-00-001", "Invalid parameter included"),
  RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR-00-002", "Resource not exists"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERR-00-003", "Internal server error"),
  ;

  private final HttpStatus httpStatus;

  private final String errorCode;

  private final String errorMsg;


}
