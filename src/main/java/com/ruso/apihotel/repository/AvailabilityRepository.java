package com.ruso.apihotel.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ruso.apihotel.model.dao.AvailabilityDAO;
import com.ruso.apihotel.model.dao.AvailabilityIdentity;

@Repository
public interface AvailabilityRepository
    extends JpaRepository<AvailabilityDAO, AvailabilityIdentity> {

  @Query("Select a From  AvailabilityDAO a Where a.availabilityIdentity.hotel_id = :idHotel AND a.availabilityIdentity.date BETWEEN :from AND :to ")
  List<AvailabilityDAO> getAvailabilityHotelInDate(LocalDate from, LocalDate to, Long idHotel);
}
