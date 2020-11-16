package com.ruso.apihotel.model.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class BookingDTO {

  private Long id;
  @Positive(message="Los ID de hoteles son positivos")
  private Long hotelId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  @Min(1)
  private Integer numRooms;
  @Email(message="El formato de email no es válido")
  private String email;

}
