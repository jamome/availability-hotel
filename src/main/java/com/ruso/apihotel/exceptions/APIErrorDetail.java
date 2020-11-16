package com.ruso.apihotel.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class APIErrorDetail {
  @ApiModelProperty(value = "The timestamp", required = true)
  private LocalDateTime timestamp;
  @ApiModelProperty(value = "The errors", required = true)
  private Map<String, String> errors = new HashMap<>();
  @JsonIgnore
  @ApiModelProperty(value = "The errors", required = false)
  private HttpStatus httpStatus;
  @ApiModelProperty(value = "The request path", required = false)
  private String path;
}
