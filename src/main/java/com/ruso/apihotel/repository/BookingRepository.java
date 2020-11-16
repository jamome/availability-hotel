package com.ruso.apihotel.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruso.apihotel.model.dao.BookingDAO;

public interface BookingRepository extends JpaRepository<BookingDAO, Long> {
  @Query("SELECT b FROM BookingDAO b WHERE b.hotel_id = :idHotel AND b.date_from >= :dateFrom AND b.date_to <= :dateTo")
  List<BookingDAO> findBookinHotelByDate(Long idHotel, LocalDate dateFrom, LocalDate dateTo);
}
