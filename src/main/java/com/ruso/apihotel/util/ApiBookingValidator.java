package com.ruso.apihotel.util;

import java.time.LocalDate;
import com.ruso.apihotel.exceptions.ApiHotelException;

public class ApiBookingValidator {

  public static void validateDateRange(LocalDate from, LocalDate to) throws ApiHotelException {
    if (from.compareTo(to) > 0) {
      ApiHotelException ex = new ApiHotelException();
      ex.getErrors().put(Constants.STR_IVALID_RANGE_DATE_ERROR_KEY,
          Constants.STR_IVALID_RANGE_DATE_ERROR_MSG);
      throw ex;
    }
  }


}
