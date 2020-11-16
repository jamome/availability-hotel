package com.ruso.apihotel.model.dao;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "hotel",uniqueConstraints={@UniqueConstraint(columnNames = "name")})
public class HotelDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "category", nullable = false)
  private Integer category;
  
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private List<AvailabilityDAO> availability;
  
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private List<BookingDAO> booking;
  
  
  

}
