package com.ruso.apihotel.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.ruso.apihotel.model.dto.AvailabilityDTO;
import com.ruso.apihotel.model.dto.CreateAvailabilityDTO;
import com.ruso.apihotel.repository.AvailabilityRepository;
import com.ruso.apihotel.services.AvailabilityService;
import com.ruso.apihotel.services.impl.AvailabilityServiceImpl;

public class AvailavilityServiceTest extends SingleContextTest {

  @Mock
  private AvailabilityRepository availabilityRespository;

  @InjectMocks
  private AvailabilityService availabilityService = new AvailabilityServiceImpl();

  private List<AvailabilityDAO> availabilityDAOEmpty = new ArrayList<AvailabilityDAO>();

  private List<AvailabilityDAO> availabilityDAOToSave = new ArrayList<AvailabilityDAO>();

  private List<AvailabilityDTO> availabilityDTOToResponse = new ArrayList<AvailabilityDTO>();

  private CreateAvailabilityDTO newAviability = new CreateAvailabilityDTO();

  @BeforeEach
  public void setup() {

    AvailabilityDAO availability = new AvailabilityDAO();
    AvailabilityIdentity id = new AvailabilityIdentity();
    id.setDate(LocalDate.parse("2020-01-01"));
    id.setHotel_id((long) 1);
    availability.setAvailabilityIdentity(id);
    availability.setRooms(50);
    availabilityDAOToSave.add(availability);

    AvailabilityDTO availabilityDTO = new AvailabilityDTO();
    availabilityDTO.setAvailableRooms(50);
    availabilityDTO.setDate(LocalDate.parse("2020-01-01"));
    availabilityDTO.setHotelId((long) 1);
    availabilityDTOToResponse.add(availabilityDTO);

    newAviability.setDateFrom(LocalDate.parse("2020-01-01"));
    newAviability.setDateTo(LocalDate.parse("2020-01-01"));
    newAviability.setIdHotel((long) 1);
    newAviability.setNumRooms(50);

  }

  @Test
  public void initAvaliabilityHotelTest() {
    Mockito
        .when(availabilityRespository.getAvailabilityHotelInDate(Mockito.any(LocalDate.class),
            Mockito.any(LocalDate.class), Mockito.any(Long.class)))
        .thenReturn(availabilityDAOEmpty);
    Mockito.when(availabilityRespository.saveAll(Mockito.anyList()))
        .thenReturn(availabilityDAOToSave);
    List<AvailabilityDTO> response = availabilityService.initAvaliabilityHotel(newAviability);
    
    Assert.assertArrayEquals(availabilityDTOToResponse.toArray(), response.toArray());
  }
  
  @Test
  public void initAvaliabilityHotelTest_NO_OK() {
    newAviability.setDateFrom(LocalDate.parse("2020-02-01"));
    newAviability.setDateTo(LocalDate.parse("2020-01-01"));
    
    assertThrows(ApiHotelException.class, () -> {
      availabilityService.initAvaliabilityHotel(newAviability);
    });
  }

}
