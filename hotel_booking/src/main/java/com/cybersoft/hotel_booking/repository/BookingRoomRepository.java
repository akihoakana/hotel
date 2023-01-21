package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.DTO.HotelSearchDTO;
import com.cybersoft.hotel_booking.entity.BookingRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRoomRepository  extends JpaRepository<BookingRoomEntity,Integer> {
    @Query(nativeQuery = true)
     List<HotelSearchDTO>findBookingRoomByHotelIdAndAndBookingId(int hotelId,int bookingId);
}
