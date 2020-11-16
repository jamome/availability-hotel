package com.ruso.apihotel.controllers.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.controllers.HotelsController;
import com.ruso.apihotel.model.dto.HotelBaseDTO;
import com.ruso.apihotel.model.dto.HotelDTO;
import com.ruso.apihotel.model.dto.HotelDTOExtended;
import com.ruso.apihotel.services.HotelService;
import io.swagger.annotations.ApiParam;

@RestController
public class HotelsControllerImpl implements HotelsController {

  @Autowired
  private HotelService hotelService;

  @Override
  @GetMapping(value = "/api/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getHotels() throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    List<HotelDTO> listHotel = hotelService.getHotels();
    return new ResponseEntity<Object>(listHotel, HttpStatus.OK);
  }

  @Override
  @PostMapping(value = "/api/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createHotel(HotelBaseDTO hotel) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    HotelDTO newHotel = hotelService.createHotel(hotel);
    return new ResponseEntity<Object>(newHotel, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping(value = "/api/v1/hotels/{idHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteHotel(long idHotel) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    hotelService.deleteHotel(idHotel);
    return new ResponseEntity<Object>("Hotel borrado satisfactoriamente", HttpStatus.OK);
  }

  @Override
  @PutMapping(value = "/api/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> updateHotel(HotelDTO hotel) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    HotelDTO updateedHotel = hotelService.updateHotel(hotel);
    return new ResponseEntity<Object>(updateedHotel, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/api/v1/hotels/{idHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getHotel(long idHotel) throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    HotelDTO hotel = hotelService.getHotel(idHotel);
    return new ResponseEntity<Object>(hotel, HttpStatus.OK);
  }

  @Override
  @GetMapping(value = "/api/v1/hotels/availability", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getHotelWithAvailability(String dateFrom, String dateTo)
      throws RuntimeException {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());
    LocalDate from = LocalDate.parse(dateFrom);
    LocalDate to = LocalDate.parse(dateTo);

    List<HotelDTOExtended> listHotel = hotelService.getHotelsWithAvailability(from, to);
    return new ResponseEntity<Object>(listHotel, HttpStatus.OK);
  }

}
