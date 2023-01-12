package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.Imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/{typecity}")
public class CityController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityProvinceServiceImp cityProvinceServiceImp;

    @PostMapping("/search")
    public ResponseEntity<?> search(@PathVariable("typecity") String typeCity) {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllByType(typeCity));
    }
    @PostMapping("/{name}")
    public ResponseEntity<?> findAllAccommodationByTypeAndName(@PathVariable("typecity") String typeCity, @PathVariable("name") String name) {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByTypeAndName(typeCity,name));
    }
    @PostMapping("")
    public ResponseEntity<?> findAllAccommodationByType(@PathVariable("typecity") String typeCity) {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByType(typeCity)) ;
    }
//    @PostMapping("/{name}")
//    public ResponseEntity<?> findAllAccommodationByTypeAndName(@PathVariable("typecity") String typeCity, @PathVariable("name") String name) {
//        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByTypeAndName(typeCity,name));
//    }
//    @PostMapping("")
//    public ResponseEntity<?> findAllAccommodationByType(@PathVariable("typecity") String typeCity) {
//        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByType(typeCity)) ;
//    }
    @PostMapping("/findall")
    public ResponseEntity<?> findall(@PathVariable("typecity") String typeCity) {
        if (typeCity.equals("city")){
            return ResponseEntity.ok(cityRepository.findAll());
        }
        else
            return ResponseEntity.ok(provinceRepository.findAll());
    }
}
