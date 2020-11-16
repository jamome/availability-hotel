package com.ruso.apihotel.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import lombok.Data;

@Data
public class HotelBaseDTO {
  
  @NotNull(message="EL nombre no puede estar vacío")
  @NotBlank(message= "El nombre no puede esta vacío")
  private String name;
  @NotNull(message = "La categoría no puede ser nula")
  @Range(min = 1,max = 5, message="La categoría debe de ser un numero positivo entre 1 y 5")
  private Integer category;
}
