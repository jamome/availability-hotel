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
import com.ruso.apihotel.model.dao.HotelDAO;
import com.ruso.apihotel.model.dto.HotelAvailabilityDTO;
import com.ruso.apihotel.model.dto.HotelBaseDTO;
import com.ruso.apihotel.model.dto.HotelDTO;
import com.ruso.apihotel.model.dto.HotelDTOExtended;
import com.ruso.apihotel.repository.HotelRepository;
import com.ruso.apihotel.services.HotelService;
import com.ruso.apihotel.services.impl.HotelServiceImpl;

public class HotelServiceTest extends SingleContextTest {

  @Mock
  private HotelRepository hotelRepository;

  @InjectMocks
  private HotelService hotelService = new HotelServiceImpl();

  private List<HotelDAO> listHotelsDAO = new ArrayList<HotelDAO>();

  private List<HotelDTO> listHotelDTO = new ArrayList<HotelDTO>();

  private List<HotelDTOExtended> listHotelDTOExtended = new ArrayList<HotelDTOExtended>();

  Optional<HotelDAO> optionalDAO;

  @BeforeEach
  public void setup() {
    HotelDAO hotelDAO1 = new HotelDAO();
    List<AvailabilityDAO> listAvailability = new ArrayList<AvailabilityDAO>();
    AvailabilityDAO availability = new AvailabilityDAO();
    AvailabilityIdentity id = new AvailabilityIdentity();
    id.setHotel_id((long) 0);
    id.setDate(LocalDate.parse("2020-01-01"));
    availability.setAvailabilityIdentity(id);
    availability.setRooms(50);
    listAvailability.add(availability);
    hotelDAO1.setCategory(0);
    hotelDAO1.setId((long) 0);
    hotelDAO1.setName("Hotel 0");
    hotelDAO1.setAvailability(listAvailability);

    HotelDAO hotelDAO2 = new HotelDAO();
    hotelDAO2.setCategory(2);
    hotelDAO2.setId((long) 2);
    hotelDAO2.setName("Hotel 2");
    HotelDAO hotelDAO3 = new HotelDAO();
    hotelDAO3.setCategory(3);
    hotelDAO3.setId((long) 3);
    hotelDAO3.setName("Hotel 3");
    listHotelsDAO.add(hotelDAO1);
    listHotelsDAO.add(hotelDAO2);
    listHotelsDAO.add(hotelDAO3);

    HotelDTO hotelDTO1 = new HotelDTO();
    hotelDTO1.setCategory(0);
    hotelDTO1.setId((long) 0);
    hotelDTO1.setName("Hotel 0");
    HotelDTO hotelDTO2 = new HotelDTO();
    hotelDTO2.setCategory(2);
    hotelDTO2.setId((long) 2);
    hotelDTO2.setName("Hotel 2");
    HotelDTO hotelDTO3 = new HotelDTO();
    hotelDTO3.setCategory(3);
    hotelDTO3.setId((long) 3);
    hotelDTO3.setName("Hotel 3");
    listHotelDTO.add(hotelDTO1);
    listHotelDTO.add(hotelDTO2);
    listHotelDTO.add(hotelDTO3);

    optionalDAO = Optional.of(listHotelsDAO.get(0));

    List<HotelAvailabilityDTO> listAvailabilityDTO = new ArrayList<HotelAvailabilityDTO>();
    HotelAvailabilityDTO availabilityDTO = new HotelAvailabilityDTO();
    availabilityDTO.setNumRoomsAvailables(50);
    availabilityDTO.setDate(LocalDate.parse("2020-01-01"));
    listAvailabilityDTO.add(availabilityDTO);
    HotelDTOExtended hotelDTOExtended = new HotelDTOExtended();
    hotelDTOExtended.setAvailability(listAvailabilityDTO);
    hotelDTOExtended.setCategory(0);
    hotelDTOExtended.setId((long) 0);
    hotelDTOExtended.setName("Hotel 0");
    listHotelDTOExtended.add(hotelDTOExtended);

  }

  @Test
  public void getHotelsTest() {
    Mockito.when(hotelRepository.findAll()).thenReturn(listHotelsDAO);
    List<HotelDTO> response = hotelService.getHotels();
    Assert.assertArrayEquals(listHotelDTO.toArray(), response.toArray());
  }

  @Test
  public void getHotelTest() {
    Mockito.when(hotelRepository.findById(Mockito.anyLong())).thenReturn(optionalDAO);
    HotelDTO response = hotelService.getHotel(Long.valueOf("1"));
    Assert.assertEquals(listHotelDTO.get(0), response);
  }

  @Test
  public void updateHotelTest() {
    Mockito.when(hotelRepository.save(Mockito.any(HotelDAO.class)))
        .thenReturn(listHotelsDAO.get(0));
    HotelDTO response = hotelService.updateHotel(Mockito.mock(HotelDTO.class));
    Assert.assertEquals(listHotelDTO.get(0), response);
  }

  @Test
  public void createHotelTest() {
    Mockito.when(hotelRepository.save(Mockito.any(HotelDAO.class)))
        .thenReturn(listHotelsDAO.get(0));
    HotelDTO response = hotelService.createHotel(Mockito.mock(HotelBaseDTO.class));
    Assert.assertEquals(listHotelDTO.get(0), response);
  }

  @Test
  public void deleteHotelTest() {
    Mockito.doNothing().when(hotelRepository).deleteById(Mockito.anyLong());
    hotelService.deleteHotel(Long.valueOf("1"));
    Assert.assertTrue(true);
  }

  @Test
  public void getHotelsWithAvailabilityTest() {
    Mockito.when(hotelRepository.findAll()).thenReturn(listHotelsDAO.subList(0, 1));
    List<HotelDTOExtended> response = hotelService
        .getHotelsWithAvailability(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-01"));
    Assert.assertArrayEquals(listHotelDTOExtended.toArray(), response.toArray());
  }

  @Test
  public void getHotelsWithAvailabilityTest_NO_OK() {
    assertThrows(ApiHotelException.class, () -> {
      hotelService.getHotelsWithAvailability(LocalDate.parse("2020-02-01"),
          LocalDate.parse("2020-01-01"));
    });
  }

}
