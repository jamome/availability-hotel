package com.ruso.apihotel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ruso.apihotel.exceptions.APIErrorDetail;
import com.ruso.apihotel.model.dao.HotelDAO;
import com.ruso.apihotel.model.dto.CreateAvailabilityDTO;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface AvailabilityController {

  @ApiOperation(notes = "Servicio para inicializar la disponibilidad de un hotel",
      response = HotelDAO.class, value = "Abrir disponibilidad", tags = "Availability")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> initAvailability(@ApiParam(value="Objeto para crear una disponibilidad en un hotel")
      @NotNull @RequestBody(required=true) CreateAvailabilityDTO availability) throws RuntimeException;
}
