package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity,Integer> {
    public HotelEntity findByHotelName(String hotelName);
}