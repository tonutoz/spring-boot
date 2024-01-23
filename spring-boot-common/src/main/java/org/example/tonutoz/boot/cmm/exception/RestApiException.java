package io.whatap.assignment.cmm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

  private final ErrorType errorType;

}
