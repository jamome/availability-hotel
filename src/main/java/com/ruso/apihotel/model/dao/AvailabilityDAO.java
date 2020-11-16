package com.ruso.apihotel.model.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "availability")
public class AvailabilityDAO {

  @EmbeddedId
  private AvailabilityIdentity availabilityIdentity;

  @Column(name = "rooms", nullable = false)
  private Integer rooms;
  
  @ManyToOne(targetEntity = HotelDAO.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
  private HotelDAO hotel;
  
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    AvailabilityDAO objectToCompare = (AvailabilityDAO) o;

    return getAvailabilityIdentity().equals(objectToCompare.getAvailabilityIdentity());
  }
}
