package io.whatap.assignment.cmm.exception;

import org.springframework.http.HttpStatus;

public interface ErrorType {

  String name();

  HttpStatus getHttpStatus();

  String getErrorCode();

  String getErrorMsg();

}
