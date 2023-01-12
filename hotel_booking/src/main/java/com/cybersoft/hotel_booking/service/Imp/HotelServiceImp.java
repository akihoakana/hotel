package com.cybersoft.hotel_booking.service.Imp;

import com.cybersoft.hotel_booking.repository.HotelRepository;
import com.cybersoft.hotel_booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
}
