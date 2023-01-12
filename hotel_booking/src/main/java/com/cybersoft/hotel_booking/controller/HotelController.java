package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.entity.HotelEntity;
import com.cybersoft.hotel_booking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/findall")
    public List<HotelEntity> findall() {
        return hotelRepository.findAll();
    }
}
