package com.ruso.apihotel.exceptions;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiHotelException extends RuntimeException {

  Map<String, String> errors = new HashMap<>();
}
