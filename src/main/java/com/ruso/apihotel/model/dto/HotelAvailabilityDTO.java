package com.ruso.apihotel.model.dto;

import java.time.LocalDate;
import lombok.Data;

@Data   
public class HotelAvailabilityDTO {
  private LocalDate date;
  private Integer numRoomsAvailables;
}
