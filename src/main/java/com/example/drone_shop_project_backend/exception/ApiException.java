package com.example.drone_shop_project_backend.exception;

import com.example.drone_shop_project_backend.exception.response.ApiError;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.exception.response.Severity;
import java.util.Collection;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ApiException extends RuntimeException {

  private Severity type;
  private String status;
  private String field;
  private String code;
  private String message;
  private HttpStatus httpStatus;
  private Collection<ApiError> errors;

  public ApiException(Collection<ApiError> errors) {
    this.errors = errors;
  }

  public ApiException(ErrorType errorType) {
    this.type = errorType.getType();
    this.status = errorType.getStatus();
    this.code = errorType.name();
    this.message = errorType.getMessage();
    this.httpStatus = errorType.getHttpStatus();
  }

  public ApiException(ErrorType errorType, String field) {
    this(errorType);
    this.field = field;
  }
}
