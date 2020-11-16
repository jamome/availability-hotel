package com.ruso.apihotel.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.exceptions.APIErrorDetail;
import com.ruso.apihotel.exceptions.ApiHotelException;
import com.ruso.apihotel.mapper.BookingHotelMapper;
import com.ruso.apihotel.model.dao.AvailabilityDAO;
import com.ruso.apihotel.model.dao.BookingDAO;
import com.ruso.apihotel.model.dto.BookingDTO;
import com.ruso.apihotel.repository.AvailabilityRepository;
import com.ruso.apihotel.repository.BookingRepository;
import com.ruso.apihotel.services.BookingService;
import com.ruso.apihotel.util.ApiBookingValidator;
import com.ruso.apihotel.util.Constants;

@Service
public class BookingServiceImpl implements BookingService {

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private AvailabilityRepository availabilityRepository;

  private BookingHotelMapper mapper = Mappers.getMapper(BookingHotelMapper.class);

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public BookingDTO createBooking(BookingDTO newBooking) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    ApiBookingValidator.validateDateRange(newBooking.getDateFrom(), newBooking.getDateTo());

    // Comrpobar la disponibilidad
    List<AvailabilityDAO> listAvailability = getActualAvailability(newBooking);
    // Se comprueba si para todos los días en el rango de fechas, hay
    // tantas habitaciones como se desea, en caso contrario se lanza
    // excepción indicando que no hay disponibles el número de habitaciones
    // deseadas para ese rango de fechas.
    List<AvailabilityDAO> listDaysWhithoutRooms = listAvailability.stream()
        .filter(a -> a.getRooms() < newBooking.getNumRooms()).collect(Collectors.toList());
    if (!listDaysWhithoutRooms.isEmpty()) {
      ApiHotelException ex = new ApiHotelException();
      ex.getErrors().put(Constants.STR_NO_AVAILABILITY_ERROR_KEY,
          Constants.STR_NO_AVAILABILITY_ERROR_MSG);
      throw ex;
    }
    // Generar actualizacion de disponibilidad y persistir
    listAvailability.forEach(a -> a.setRooms(a.getRooms() - newBooking.getNumRooms()));
    availabilityRepository.saveAll(listAvailability);
    // Realizar la reserva
    return mapper
        .BookingDAOToBookingDTO(bookingRepository.save(mapper.BookingDTOToBookingDAO(newBooking)));
  }

  @Override
  public BookingDTO getBooking(Long idBooking) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    return mapper.BookingDAOToBookingDTO(bookingRepository.getOne(idBooking));
  }

  @Override
  public List<BookingDTO> getHotelBooking(Long idHotel, LocalDate dateFrom, LocalDate dateTo) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    ApiBookingValidator.validateDateRange(dateFrom, dateTo);

    return mapper.ListBookingDAOToListBookingDTO(
        bookingRepository.findBookinHotelByDate(idHotel, dateFrom, dateTo));
  }

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public void deleteBooking(Long idBooking) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    // Obtenemos los datos de la reserva
    Optional<BookingDAO> booking = bookingRepository.findById(idBooking);
    if (booking.isPresent()) {
      List<AvailabilityDAO> listAvailability =
          getActualAvailability(mapper.BookingDAOToBookingDTO(booking.get()));
      listAvailability.forEach(a -> a.setRooms(a.getRooms() + booking.get().getNum_rooms()));
      availabilityRepository.saveAll(listAvailability);
      bookingRepository.deleteById(idBooking);
    }

  }

  private List<AvailabilityDAO> getActualAvailability(BookingDTO booking) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    List<AvailabilityDAO> listAvailability = availabilityRepository.getAvailabilityHotelInDate(
        booking.getDateFrom(), booking.getDateTo(), booking.getHotelId());
    return listAvailability;
  }
}
