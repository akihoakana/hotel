package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.DTO.CityProvinceDTO;
import com.cybersoft.hotel_booking.DTO.CitySearchDTO;
import com.cybersoft.hotel_booking.DTO.ProvinceSearchDTO;
import com.cybersoft.hotel_booking.model.CityModel;
import com.cybersoft.hotel_booking.model.CityProvinceModel;
import com.cybersoft.hotel_booking.model.HotelModel;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.Imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/{typecity}/search")
public class SortHotelController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityProvinceServiceImp cityProvinceServiceImp;
    @PostMapping("/{sort}")
    public ResponseEntity<?> search(@PathVariable("typecity") String typeCity, @PathVariable("sort") String sort) {
        return ResponseEntity.ok(cityProvinceServiceImp.search(typeCity,sort));
    }
    @PostMapping("/{name}/{sort}")
    public ResponseEntity<?> searchName(@PathVariable("typecity") String typeCity,@PathVariable("name") String name, @PathVariable("sort") String sort) {
        return ResponseEntity.ok(cityProvinceServiceImp.searchName(typeCity,name,sort));
    }
}