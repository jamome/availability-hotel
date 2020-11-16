package com.ruso.apihotel.model.dao;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class BookingDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "hotel_id", nullable = false)
  private Long hotel_id;

  @Column(name = "date_from", nullable = false)
  private LocalDate date_from;

  @Column(name = "date_to", nullable = false)
  private LocalDate date_to;

  @Column(name = "num_rooms", nullable = false)
  private Integer num_rooms;

  @Column(name = "email", nullable = false)
  private String email;

  @ManyToOne(targetEntity = HotelDAO.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
  private HotelDAO hotel;

}
