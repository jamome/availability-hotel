package com.ruso.apihotel.controllers.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.controllers.BookingController;
import com.ruso.apihotel.model.dto.BookingDTO;
import com.ruso.apihotel.services.BookingService;

@RestController
public class BookingControllerImpl implements BookingController {

  @Autowired
  private BookingService bookingService;

  @Override
  @PostMapping(value = "/api/v1/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> doBooking(BookingDTO booking) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    BookingDTO bookingSaved = bookingService.createBooking(booking);
    return new ResponseEntity<Object>(bookingSaved, HttpStatus.CREATED);
  }

  @Override
  @GetMapping(value = "/api/v1/bookings/{idBooking}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getBooking(long idBooking) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    BookingDTO booking = bookingService.getBooking(idBooking);
    return new ResponseEntity<Object>(booking, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/api/v1/bookings/hotel/{idHotel}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getBookings(long idHotel, String dateFrom, String dateTo)
      throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    LocalDate from = LocalDate.parse(dateFrom);
    LocalDate to = LocalDate.parse(dateTo);
    List<BookingDTO> listBookingHotel = bookingService.getHotelBooking(idHotel, from, to);
    return new ResponseEntity<Object>(listBookingHotel, HttpStatus.OK);
  }

  @Override
  @DeleteMapping(value = "/api/v1/bookings/{idBooking}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteBookings(long idBooking) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    bookingService.deleteBooking(idBooking);
    return new ResponseEntity<Object>(
        "Se ha eliminado correctamente la reserva con id " + idBooking, HttpStatus.OK);
  }

}
