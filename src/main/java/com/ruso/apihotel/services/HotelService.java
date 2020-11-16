package com.ruso.apihotel.services;

import java.time.LocalDate;
import java.util.List;
import com.ruso.apihotel.model.dto.HotelBaseDTO;
import com.ruso.apihotel.model.dto.HotelDTO;
import com.ruso.apihotel.model.dto.HotelDTOExtended;

public interface HotelService {

  public List<HotelDTO> getHotels();
  
  public List<HotelDTOExtended> getHotelsWithAvailability(LocalDate from, LocalDate to);
  
  public HotelDTO getHotel(Long idHotel);

  public HotelDTO createHotel(HotelBaseDTO newHotel);

  public void deleteHotel(Long idHotel);

  public HotelDTO updateHotel(HotelDTO hotel);
}
