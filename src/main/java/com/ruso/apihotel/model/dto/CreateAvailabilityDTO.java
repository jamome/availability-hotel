package com.ruso.apihotel.model.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateAvailabilityDTO {

  private Long idHotel;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private Integer numRooms;
}
