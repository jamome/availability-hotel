package com.ruso.apihotel.controllers.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.controllers.AvailabilityController;
import com.ruso.apihotel.model.dto.AvailabilityDTO;
import com.ruso.apihotel.model.dto.CreateAvailabilityDTO;
import com.ruso.apihotel.services.AvailabilityService;

@RestController
public class AvailabilityControllerImpl implements AvailabilityController {

  @Autowired
  private AvailabilityService availabilityService;

  @Override
  @PostMapping(value = "/api/v1/availability", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> initAvailability(CreateAvailabilityDTO availability)
      throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    List<AvailabilityDTO> response = availabilityService.initAvaliabilityHotel(availability);
    HttpStatus httpStatusResponse = response.isEmpty()?HttpStatus.OK:HttpStatus.CREATED;

    return new ResponseEntity<Object>(response,httpStatusResponse);
  }



}
