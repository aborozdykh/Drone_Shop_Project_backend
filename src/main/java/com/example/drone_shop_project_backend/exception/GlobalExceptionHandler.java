package com.example.drone_shop_project_backend.exception;


import static com.example.drone_shop_project_backend.exception.response.HttpStatusConstant.INTERNAL_SERVER_ERROR;

import com.example.drone_shop_project_backend.exception.response.ApiError;
import com.example.drone_shop_project_backend.exception.response.ApiResponse;
import com.example.drone_shop_project_backend.exception.response.ErrorType;
import com.example.drone_shop_project_backend.exception.response.Severity;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class})
@AllArgsConstructor
public class GlobalExceptionHandler {

  private static ApiResponse buildUnsuccessfulResponse(Collection<ApiError> errors) {
    return ApiResponse.builder()
        .success(false)
        .errors(errors)
        .build();
  }

  @ExceptionHandler(ApiException.class)
  @ResponseBody
  public ApiResponse processApiException(HttpServletResponse response,
      ApiException exception) {
    Collection<ApiError> errors = exception.getErrors();
    if (errors != null) {
      return buildUnsuccessfulResponse(errors);
    } else {
      response.setStatus(exception.getHttpStatus().value());
      exception.printStackTrace();
      var apiError = getApiError(exception);
      return buildUnsuccessfulResponse(List.of(apiError));
    }
  }

//  @ExceptionHandler({AuthenticationException.class})
//  @ResponseBody
//  public ApiResponse processInternalAuthenticationServiceException(HttpServletResponse response,
//      AuthenticationException exception) {
//    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//    //TODO Make print stacktrace only in debug mode
//    exception.printStackTrace();
//    var apiError = getApiError(exception);
//    return buildUnsuccessfulResponse(List.of(apiError));
//  }

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public ApiResponse uncheckedExceptionHandler(HttpServletResponse response, Throwable throwable) {
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    //TODO Make print stacktrace only in debug mode
    throwable.printStackTrace();
    var apiError = getApiError(throwable);
    return buildUnsuccessfulResponse(List.of(apiError));
  }

  private ApiError getApiError(ApiException exception) {
    return ApiError.builder()
        .type(exception.getType())
        .code(exception.getCode())
        .field(exception.getField())
        .status(exception.getStatus())
        .message(exception.getMessage())
        .build();
  }

//  private ApiError getApiError(AuthenticationException exception) {
//    return ApiError.builder()
//        .type(Severity.ERROR)
//        .code(ErrorType.USER_UNAUTHORIZED.name())
//        .status(UNAUTHORIZED)
//        .message(exception.getMessage())
//        .build();
//  }

  private ApiError getApiError(Throwable throwable) {
    return ApiError.builder()
        .type(Severity.FATAL)
        .code(ErrorType.SERVER_ERROR.name())
        .status(INTERNAL_SERVER_ERROR)
        .message(throwable.getMessage())
        .build();
  }
}
