package com.ruso.apihotel.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.model.dao.AvailabilityDAO;
import com.ruso.apihotel.model.dao.AvailabilityIdentity;
import com.ruso.apihotel.model.dto.AvailabilityDTO;
import com.ruso.apihotel.model.dto.CreateAvailabilityDTO;
import com.ruso.apihotel.repository.AvailabilityRepository;
import com.ruso.apihotel.services.AvailabilityService;
import com.ruso.apihotel.util.ApiBookingValidator;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

  @Autowired
  private AvailabilityRepository availabilityRepository;

  @Override
  public List<AvailabilityDTO> initAvaliabilityHotel(CreateAvailabilityDTO availability) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    ApiBookingValidator.validateDateRange(availability.getDateFrom(), availability.getDateTo());

    List<AvailabilityDAO> actualAvailability = availabilityRepository.getAvailabilityHotelInDate(
        availability.getDateFrom(), availability.getDateTo(), availability.getIdHotel());;

    List<AvailabilityDAO> listAvailabilityToInit =
        generateAvailabilityDAOFromCreateAvailability(availability);

    listAvailabilityToInit.removeIf(a -> actualAvailability.contains(a));

    List<AvailabilityDAO> listAvailabilityInitiated =
        availabilityRepository.saveAll(listAvailabilityToInit);

    List<AvailabilityDTO> listAvailabilityResponse = new ArrayList<AvailabilityDTO>();

    listAvailabilityInitiated.stream().forEach(a -> {
      AvailabilityDTO av = new AvailabilityDTO();
      av.setAvailableRooms(a.getRooms());
      av.setDate(a.getAvailabilityIdentity().getDate());
      av.setHotelId(a.getAvailabilityIdentity().getHotel_id());
      listAvailabilityResponse.add(av);
    });

    return listAvailabilityResponse;
  }

  private List<AvailabilityDAO> generateAvailabilityDAOFromCreateAvailability(
      CreateAvailabilityDTO createAvailability) {
    ApiLogger.logWhere(this.getClass().getSimpleName(),
        Thread.currentThread().getStackTrace()[1].getMethodName());

    LocalDate from = createAvailability.getDateFrom();
    LocalDate to = createAvailability.getDateTo();

    List<AvailabilityDAO> listAvailability = new ArrayList<AvailabilityDAO>();

    for (; from.compareTo(to) <= 0; from = from.plusDays(1)) {
      AvailabilityDAO availability = new AvailabilityDAO();
      AvailabilityIdentity id = new AvailabilityIdentity();
      id.setDate(from);
      id.setHotel_id(createAvailability.getIdHotel());

      availability.setAvailabilityIdentity(id);
      availability.setRooms(createAvailability.getNumRooms());

      listAvailability.add(availability);
    }

    return listAvailability;
  }

}
