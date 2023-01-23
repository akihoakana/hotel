package com.cybersoft.hotel_booking.service.Imp;

import com.cybersoft.hotel_booking.DTO.HotelSearchDTO;
import com.cybersoft.hotel_booking.repository.BookingRoomRepository;
import com.cybersoft.hotel_booking.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private BookingRoomRepository bookingRoomRepository;
    private List<?> findAll(String typeCity, List<?> provinceEntityList,String checkIn,String checkOut,int hotelId,int maxOccupyAdult,int maxOccupyChild) {
        List<HotelSearchDTO>hotelSearchDTOList= bookingRoomRepository.findBookingRoomByHotelIdAndAndBookingId(checkIn,checkOut,hotelId,maxOccupyAdult,maxOccupyChild);
        return null;
    }
}
