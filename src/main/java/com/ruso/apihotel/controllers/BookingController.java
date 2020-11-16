package com.ruso.apihotel.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.ruso.apihotel.exceptions.APIErrorDetail;
import com.ruso.apihotel.model.dto.BookingDTO;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface BookingController {

  @ApiOperation(notes = "Servicio para realizar una reserva", response = BookingDTO.class,
      value = "Reserva de habitacion de hoteles", tags = "Bookings")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> doBooking(@Valid @NotNull @ApiParam(
      value = "Objeto para crear una reserva, el ID no es necesario, se genera automáticamente") @RequestBody(
          required = true) BookingDTO booking)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio para obtener una reserva", response = BookingDTO.class,
      value = "Obtener reserva", tags = "Bookings")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> getBooking(
      @Valid @ApiParam(value = "Identificador de la reserva que se desea obtener") @Positive(
          message = "El identificador de la reserva debe de ser positivo") @PathVariable("idBooking") long idBooking)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio para obtener las reservas de un Hotel dadas unas fechas",
      response = BookingDTO.class, value = "Obtener reservas de un hotel", tags = "Bookings")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> getBookings(@ApiParam(
      value = "Identificador del hotel del que se quieren consultar las reservas") @PathVariable("idHotel") long idHotel,
      @ApiParam(value = "Inicio del rango de fecha a consultar (AAAA-MM-DD)") @RequestParam(
          name = "dateFrom", required = true) String from,
      @ApiParam(value = "Fin del rango de fecha a consultar (AAAA-MM-DD)") @RequestParam(
          name = "dateTo", required = true) String to)
      throws RuntimeException;

  @ApiOperation(notes = "Servicio para una reserva por ID", response = String.class,
      value = "Eliminar reserva", tags = "Bookings")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(code = 500, message = "Internal Server Error", response = APIErrorDetail.class),
      @ApiResponse(code = 400, message = "Bad Request", response = APIErrorDetail.class),
      @ApiResponse(code = 404, message = "Resource not found", response = APIErrorDetail.class),
      @ApiResponse(code = 403, message = "Forbiden", response = APIErrorDetail.class)})
  public ResponseEntity<Object> deleteBookings(
      @ApiParam("Identificador de la reserva que se quiere eliminar") @PathVariable("idBooking") long idBooking)
      throws RuntimeException;
}
