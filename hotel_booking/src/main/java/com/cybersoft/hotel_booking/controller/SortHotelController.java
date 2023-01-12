package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.DTO.CitySearchDTO;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.Imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/{typecity}")
public class SortHotelController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityProvinceServiceImp cityProvinceServiceImp;

//    @PostMapping("/search/{sort}")
//    public ResponseEntity<?> search(@PathVariable("typecity") String typeCity, @PathVariable("sort") String sort) {
//        List<CitySearchDTO> list = cityProvinceServiceImp.findAllByType(typeCity);
//        list.sort(((o1, o2) -> o1.g));
//        return ResponseEntity.ok("ok");
//    }
}