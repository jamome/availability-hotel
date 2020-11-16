package com.ruso.apihotel.controllers;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ruso.apihotel.exceptions.APIErrorDetail;
import com.ruso.apihotel.model.dao.HotelDAO;
import com.ruso.apihotel.model.dto.HotelBaseDTO;
import com.ruso.apihotel.model.dto.HotelDTO;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface HotelsController {

  @ApiOperation(notes = "Servicio de obtencion de Hoteles dados de alta", response = HotelDTO.class,
      value = "Obtención de Hoteles", tags = "Hotels")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> getHotels() throws RuntimeException;

  @ApiOperation(notes = "Servicio para dar de alta un Hotel", response = HotelDAO.class,
      value = "Alta de Hotel", tags = "Hotels")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> createHotel(
      @Valid @ApiParam(value = "Objeto para crear una reserva") @NotNull @RequestBody(
          required = true) HotelBaseDTO hotel)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio para eliminar un Hotel", response = HotelDTO.class,
      value = "Eliminación de Hotel", tags = "Hotels")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> deleteHotel(
      @ApiParam(value = "Identificador del Hotel a eliminar") @PathVariable("idHotel") long idHotel)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio de actualización de hotel", response = HotelDTO.class,
      value = "Actualización de Hotel", tags = "Hotels")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> updateHotel(@ApiParam(
      value = "Datos del Hotel a actualizar") @NotNull @RequestBody(required = true) HotelDTO hotel)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio de obtencion de un Hotel por ID", response = HotelDAO.class,
      value = "Obtención de Hoteles por ID", tags = "Hotels")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> getHotel(
      @ApiParam(value = "Identificador del hotel") @PathVariable("idHotel") long idHotel)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio de obtencion de Hoteles con disponibilidad en fechas",
      response = HotelDTO.class, value = "Obtención de Hoteles con disponibilidad", tags = "Hotels")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> getHotelWithAvailability(
      @ApiParam(value = "Inicio del rango de fecha a consultar (AAAA-MM-DD)") @RequestParam(
          name = "dateFrom", required = true) String from,
      @ApiParam(value = "Fin del rango de fecha a consultar (AAAA-MM-DD)") @RequestParam(
          name = "dateTo", required = true) String to)
      throws RuntimeException;
}
