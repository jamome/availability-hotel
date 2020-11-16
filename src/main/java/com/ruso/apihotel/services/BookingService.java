package com.ruso.apihotel.services;

import java.time.LocalDate;
import java.util.List;
import com.ruso.apihotel.model.dto.BookingDTO;

public interface BookingService {
  
  public BookingDTO createBooking(BookingDTO newBooking);
  
  public BookingDTO getBooking(Long idBooking);
  
  public List<BookingDTO> getHotelBooking(Long idHotel, LocalDate dateFrom, LocalDate dateTo);
  
  public void deleteBooking(Long idBooking);

}
