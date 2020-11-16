package com.ruso.apihotel.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class HotelDTOExtended extends HotelDTO {
  private List<HotelAvailabilityDTO> availability = new ArrayList<HotelAvailabilityDTO>();
}
