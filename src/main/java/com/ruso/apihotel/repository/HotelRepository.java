package com.ruso.apihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ruso.apihotel.model.dao.HotelDAO;

@Repository
public interface HotelRepository extends JpaRepository<HotelDAO, Long> {
  
}
