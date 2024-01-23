package io.whatap.assignment.cmm.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private String code;
  private String message;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<ValidationError> errors;


  @Builder
  public ErrorResponse(String code, String message, List<ValidationError> errors) {
    this.code = code;
    this.message = message;
    this.errors = errors;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class ValidationError {

    private String field;
    private String message;

    @Builder
    public ValidationError(String field, String message) {
      this.field = field;
      this.message = message;
    }

    public static ValidationError of(final FieldError fieldError) {
      return ValidationError.builder()
          .field(fieldError.getField())
          .message(fieldError.getDefaultMessage())
          .build();
    }
  }
}
