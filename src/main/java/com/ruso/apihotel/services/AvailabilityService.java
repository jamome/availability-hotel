package com.ruso.apihotel.services;

import java.util.List;
import com.ruso.apihotel.model.dto.AvailabilityDTO;
import com.ruso.apihotel.model.dto.CreateAvailabilityDTO;

public interface AvailabilityService {
  public List<AvailabilityDTO> initAvaliabilityHotel(CreateAvailabilityDTO availability);
}
