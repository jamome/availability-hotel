package com.ruso.apihotel.model.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AvailabilityDTO {

  private LocalDate date;
  private Long hotelId;
  private Integer availableRooms;
}
