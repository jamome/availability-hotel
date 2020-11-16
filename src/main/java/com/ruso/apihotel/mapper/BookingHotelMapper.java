package com.ruso.apihotel.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.ruso.apihotel.model.dao.AvailabilityDAO;
import com.ruso.apihotel.model.dao.BookingDAO;
import com.ruso.apihotel.model.dao.HotelDAO;
import com.ruso.apihotel.model.dto.AvailabilityDTO;
import com.ruso.apihotel.model.dto.BookingDTO;
import com.ruso.apihotel.model.dto.HotelBaseDTO;
import com.ruso.apihotel.model.dto.HotelDTO;

@Mapper
public interface BookingHotelMapper {

  HotelDAO HotelDTOToHotelDAO(HotelDTO source);

  HotelDAO CreateHotelDTOToHotelDAO(HotelBaseDTO source);

  HotelDTO HotelDAOToHotelDTO(HotelDAO source);

  List<HotelDAO> HotelDTOListToHotelDAOList(List<HotelDTO> source);

  List<HotelDTO> HotelDAOListToHotelDTOList(List<HotelDAO> source);



  @Mappings({@Mapping(target = "hotel_id", source = "source.hotelId"),
      @Mapping(target = "date_from", source = "source.dateFrom"),
      @Mapping(target = "date_to", source = "source.dateTo"),
      @Mapping(target = "num_rooms", source = "source.numRooms")})
  BookingDAO BookingDTOToBookingDAO(BookingDTO source);

  @Mappings({@Mapping(target = "hotelId", source = "source.hotel_id"),
      @Mapping(target = "dateFrom", source = "source.date_from"),
      @Mapping(target = "dateTo", source = "source.date_to"),
      @Mapping(target = "numRooms", source = "source.num_rooms")})
  BookingDTO BookingDAOToBookingDTO(BookingDAO source);

  List<BookingDTO> ListBookingDAOToListBookingDTO(List<BookingDAO> source);

  AvailabilityDAO AvailabilityDTOToAvailabilityDAO(AvailabilityDTO source);

  AvailabilityDTO AvailabilityDAOToAvailabilityDTO(AvailabilityDAO source);

}
