package com.ruso.apihotel.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ruso.apihotel.model.dao.HotelDAO;

@Repository
public interface HotelRepository extends JpaRepository<HotelDAO, Long> {
  @Query(
      value = "SELECT h.* FROM hotel h inner join availability av on h.id = av.hotel_id WHERE av.rooms > 0 AND av.date BETWEEN :from AND :to GROUP BY h.id",
      nativeQuery = true)
  List<HotelDAO> getHotelWithAvailabilityByDate(LocalDate from, LocalDate to);
}
