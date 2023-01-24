package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.payload.request.SearchRequest;
import com.cybersoft.hotel_booking.repository.BookingRoomRepository;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.Imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


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
    @Autowired
    private BookingRoomRepository bookingRoomRepository;
    @PostMapping("/search")
    public ResponseEntity<?> search(@PathVariable("typecity") String typeCity, @RequestBody SearchRequest searchRequest) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllByType(typeCity,searchRequest));
    }
//    @PostMapping("/abc")
//    public ResponseEntity<?> abc(@PathVariable("typecity") String typeCity , @RequestBody SearchRequest searchRequest) {
//        return ResponseEntity.ok(bookingRoomRepository.findBookingRoomByHotelIdAndAndBookingId(
//                searchRequest.getCheckIn(),searchRequest.getCheckOut()
//                ,searchRequest.getHotelId(),searchRequest.getMaxOccupyAdult()
//                ,searchRequest.getMaxOccupyChild()));
//    }
    @PostMapping("/{name}/search")
    public ResponseEntity<?> searchName(@PathVariable("typecity") String typeCity, @PathVariable("name") String name, @RequestBody SearchRequest searchRequest) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllByTypeAndName(typeCity,name,searchRequest));
    }
    @PostMapping("/{sort}")
    public ResponseEntity<?> sort(@PathVariable("typecity") String typeCity, @RequestBody SearchRequest searchRequest, @PathVariable("sort") String sort) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.sort(typeCity,sort,searchRequest));
    }
    @PostMapping("/{name}/{sort}")
    public ResponseEntity<?> sortName(@PathVariable("typecity") String typeCity, @RequestBody SearchRequest searchRequest,@PathVariable("name") String name, @PathVariable("sort") String sort) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.sortName(typeCity,name,sort,searchRequest));
    }
    @PostMapping("/{name}")
    public ResponseEntity<?> findAllAccommodationByTypeAndName(@PathVariable("typecity") String typeCity, @PathVariable("name") String name) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByTypeAndName(typeCity,name,null));
    }
    @PostMapping("")
    public ResponseEntity<?> findAllAccommodationByType(@PathVariable("typecity") String typeCity) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByType(typeCity,null)) ;
    }
    @PostMapping("/findall")
    public ResponseEntity<?> findall(@PathVariable("typecity") String typeCity) {
        if (typeCity.equals("city")){
            return ResponseEntity.ok(cityRepository.findAll());
        }
        else if (typeCity.equals("province"))
            return ResponseEntity.ok(provinceRepository.findAll());
        else
            return ResponseEntity.ok("ok");
    }

//    @PostMapping("/{name}")
//    public ResponseEntity<?> findAllAccommodationByTypeAndName(@PathVariable("typecity") String typeCity, @PathVariable("name") String name) {
//        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByTypeAndName(typeCity,name));
//    }
//    @PostMapping("")
//    public ResponseEntity<?> findAllAccommodationByType(@PathVariable("typecity") String typeCity) {
//        return ResponseEntity.ok(cityProvinceServiceImp.findAllAccommodationByType(typeCity)) ;
//    }
}
