package com.example.drone_shop_project_backend.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse {

  private static final long serialVersionUID = 5293783379142540352L;

  private boolean success;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Collection<ApiError> errors;

  public void addError(ApiError error) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(error);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ApiResponse [success=");
    builder.append(success);
    builder.append(", ");
    if (errors != null) {
      builder.append("errors=");
      builder.append(errors);
    }
    builder.append("]");
    return builder.toString();
  }

}
