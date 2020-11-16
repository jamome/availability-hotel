package com.ruso.apihotel.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.mapper.BookingHotelMapper;
import com.ruso.apihotel.model.dao.HotelDAO;
import com.ruso.apihotel.model.dto.HotelAvailabilityDTO;
import com.ruso.apihotel.model.dto.HotelBaseDTO;
import com.ruso.apihotel.model.dto.HotelDTO;
import com.ruso.apihotel.model.dto.HotelDTOExtended;
import com.ruso.apihotel.repository.HotelRepository;
import com.ruso.apihotel.services.HotelService;
import com.ruso.apihotel.util.ApiBookingValidator;

@Service
public class HotelServiceImpl implements HotelService {

  @Autowired
  private HotelRepository hotelRepository;

  private BookingHotelMapper mapper = Mappers.getMapper(BookingHotelMapper.class);

  @Override
  public List<HotelDTO> getHotels() {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    return mapper.HotelDAOListToHotelDTOList(hotelRepository.findAll());
  }

  @Override
  public HotelDTO createHotel(HotelBaseDTO newHotel) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    return mapper
        .HotelDAOToHotelDTO(hotelRepository.save(mapper.CreateHotelDTOToHotelDAO(newHotel)));
  }

  @Override
  public void deleteHotel(Long idHotel) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    hotelRepository.deleteById(idHotel);
  }

  @Override
  public HotelDTO updateHotel(HotelDTO hotel) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    return mapper.HotelDAOToHotelDTO(hotelRepository.save(mapper.HotelDTOToHotelDAO(hotel)));
  }

  @Override
  public HotelDTO getHotel(Long idHotel) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    Optional<HotelDAO> hotel = hotelRepository.findById(idHotel);
    HotelDTO hotelToReturn = hotel.isPresent() ? mapper.HotelDAOToHotelDTO(hotel.get()) : null;

    return hotelToReturn;
  }

  @Override
  public List<HotelDTOExtended> getHotelsWithAvailability(LocalDate from, LocalDate to) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    ApiBookingValidator.validateDateRange(from, to);

    List<HotelDAO> listHotelsWithAvailability =
        hotelRepository.getHotelWithAvailabilityByDate(from, to);

    List<HotelDTOExtended> listHotelToResponse = new ArrayList<HotelDTOExtended>();

    listHotelsWithAvailability.forEach(h -> {
      HotelDTOExtended hotelResponse = new HotelDTOExtended();
      List<HotelAvailabilityDTO> listAvailability = new ArrayList<HotelAvailabilityDTO>();
      hotelResponse.setCategory(h.getCategory());
      hotelResponse.setId(h.getId());
      hotelResponse.setName(h.getName());
      h.getAvailability().forEach(a -> {
        HotelAvailabilityDTO hotelAvailability = new HotelAvailabilityDTO();
        hotelAvailability.setDate(a.getAvailabilityIdentity().getDate());
        hotelAvailability.setNumRoomsAvailables(a.getRooms());
        listAvailability.add(hotelAvailability);
      });
      hotelResponse.setAvailability(listAvailability);
      listHotelToResponse.add(hotelResponse);
    });

    return listHotelToResponse;
  }


}
