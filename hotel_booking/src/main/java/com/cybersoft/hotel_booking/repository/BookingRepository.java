package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
    BookingEntity findById(int id);
}
