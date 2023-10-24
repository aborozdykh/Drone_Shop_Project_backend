package com.example.drone_shop_project_backend.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError implements Serializable {

  private static final long serialVersionUID = 3141202315514392014L;

  private Severity type;
  private String code;
  private String field;
  private String status;
  private String message;
  private String length;
  private ErrorType errorType;
}
