package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.DTO.HotelSearchDTO;
import com.cybersoft.hotel_booking.entity.BookingRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRoomRepository  extends JpaRepository<BookingRoomEntity,Integer> {
    @Query(nativeQuery = true)
     List<HotelSearchDTO>findBookingRoomByHotelIdAndAndBookingId(LocalDate checkIn, LocalDate checkOut, int hotelId, int maxOccupyAdult, int maxOccupyChild);
}
