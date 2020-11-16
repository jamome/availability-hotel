package com.ruso.apihotel.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.ruso.apihotel.base.SingleContextTest;
import com.ruso.apihotel.exceptions.ApiHotelException;
import com.ruso.apihotel.model.dao.AvailabilityDAO;
import com.ruso.apihotel.model.dao.AvailabilityIdentity;
import com.ruso.apihotel.model.dao.BookingDAO;
import com.ruso.apihotel.model.dto.BookingDTO;
import com.ruso.apihotel.repository.AvailabilityRepository;
import com.ruso.apihotel.repository.BookingRepository;
import com.ruso.apihotel.services.BookingService;
import com.ruso.apihotel.services.impl.BookingServiceImpl;

public class BookingServiceTest extends SingleContextTest {
  @Mock
  private AvailabilityRepository availabilityRepository;

  @Mock
  private BookingRepository bookingRepository;

  @InjectMocks
  private BookingService bookingService = new BookingServiceImpl();

  private BookingDTO bookingIO = new BookingDTO();

  private List<AvailabilityDAO> actualAvailability = new ArrayList<AvailabilityDAO>();

  private BookingDAO bookingDAO = new BookingDAO();


  @BeforeEach
  public void setup() {
    bookingIO.setDateFrom(LocalDate.parse("2020-01-01"));
    bookingIO.setDateTo(LocalDate.parse("2020-01-01"));
    bookingIO.setEmail("email@gmail.com");
    bookingIO.setHotelId((long) 1);
    bookingIO.setId((long) 1);
    bookingIO.setNumRooms(1);

    bookingDAO.setDate_from(LocalDate.parse("2020-01-01"));
    bookingDAO.setDate_to(LocalDate.parse("2020-01-01"));
    bookingDAO.setEmail("email@gmail.com");
    bookingDAO.setHotel_id((long) 1);
    bookingDAO.setId((long) 1);
    bookingDAO.setNum_rooms(1);

    AvailabilityDAO availability = new AvailabilityDAO();
    AvailabilityIdentity id = new AvailabilityIdentity();
    id.setDate(LocalDate.parse("2020-01-01"));
    id.setHotel_id((long) 1);
    availability.setAvailabilityIdentity(id);
    availability.setRooms(50);
    actualAvailability.add(availability);

  }

  @Test
  public void createBookingTest() {
    Mockito.when(availabilityRepository.getAvailabilityHotelInDate(Mockito.any(LocalDate.class),
        Mockito.any(LocalDate.class), Mockito.anyLong())).thenReturn(actualAvailability);
    Mockito.when(availabilityRepository.saveAll(Mockito.anyIterable())).thenReturn(null);
    Mockito.when(bookingRepository.save(Mockito.any(BookingDAO.class))).thenReturn(bookingDAO);
    BookingDTO response = bookingService.createBooking(bookingIO);
    Assert.assertEquals(bookingIO, response);
  }

  @Test
  public void createBookingTest_NO_ROOMS_OK() {
    bookingIO.setNumRooms(100);
    Mockito.when(availabilityRepository.getAvailabilityHotelInDate(Mockito.any(LocalDate.class),
        Mockito.any(LocalDate.class), Mockito.anyLong())).thenReturn(actualAvailability);
    Mockito.when(availabilityRepository.saveAll(Mockito.anyIterable())).thenReturn(null);
    Mockito.when(bookingRepository.save(Mockito.any(BookingDAO.class))).thenReturn(bookingDAO);

    assertThrows(ApiHotelException.class, () -> {
      bookingService.createBooking(bookingIO);
    });
  }
  
  @Test
  public void createBookingTest_NO_RANGE_DATE_OK() {
    bookingIO.setDateFrom(LocalDate.parse("2020-02-01"));
    bookingIO.setDateTo(LocalDate.parse("2020-01-01"));
    assertThrows(ApiHotelException.class, () -> {
      bookingService.createBooking(bookingIO);
    });
  }

  @Test
  public void getBookingTest() {
    Mockito.when(bookingRepository.getOne(Mockito.anyLong())).thenReturn(bookingDAO);
    BookingDTO response = bookingService.getBooking((long) 1);

    Assert.assertEquals(bookingIO, response);
  }

  @Test
  public void getHotelBookingTest() {
    List<BookingDAO> bookingList = new ArrayList<BookingDAO>();
    List<BookingDTO> bookingListDTO = new ArrayList<BookingDTO>();
    bookingListDTO.add(bookingIO);
    bookingList.add(bookingDAO);
    Mockito.when(bookingRepository.findBookinHotelByDate(Mockito.anyLong(),
        Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenReturn(bookingList);
    List<BookingDTO> response = bookingService.getHotelBooking((long)1,
        LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-30"));
    Assert.assertArrayEquals(bookingListDTO.toArray(), response.toArray());
  }

  @Test
  public void deleteBookingTest() {
    Optional<BookingDAO> optionalBooking = Optional.of(bookingDAO);
    Mockito.when(bookingRepository.findById(Mockito.anyLong())).thenReturn(optionalBooking);
    Mockito.when(availabilityRepository.saveAll(Mockito.anyIterable())).thenReturn(null);
    Mockito.doNothing().when(bookingRepository).deleteById(Mockito.anyLong());
    bookingService.deleteBooking((long) 1);
    Assert.assertTrue(true);
  }


}
