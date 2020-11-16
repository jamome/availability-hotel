package com.ruso.apihotel.model.dao;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@Embeddable
public class AvailabilityIdentity implements Serializable {

  @NotNull
  private LocalDate date;

  @NotNull
  private Long hotel_id;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    AvailabilityIdentity objectToCompare = (AvailabilityIdentity) o;

    if (getDate().getYear() != objectToCompare.getDate().getYear() ||
        getDate().getMonth() != objectToCompare.getDate().getMonth() ||
        getDate().getDayOfMonth() != objectToCompare.getDate().getDayOfMonth())
      return false;
    return getHotel_id().equals(objectToCompare.getHotel_id());
  }

  @Override
  public int hashCode() {
    int result = getDate().hashCode();
    result = 31 * result + getHotel_id().hashCode();
    return result;
  }

}
