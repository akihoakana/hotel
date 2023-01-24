package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.DTO.CitySearchDTO;
import com.cybersoft.hotel_booking.DTO.ProvinceSearchDTO;
import com.cybersoft.hotel_booking.model.HotelModel;
import com.cybersoft.hotel_booking.payload.request.SearchRequest;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.Imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("/{typecity}/filter")
public class FilterHotelController {

    @Autowired
    private CityProvinceServiceImp cityProvinceServiceImp;
    @PostMapping("")
    public ResponseEntity<?> filter(@PathVariable("typecity") String typeCity
            ,@RequestParam(name = "price") String price
            ,@RequestParam(name = "rate") String rate
            ,@RequestParam(name = "rank") String rank
            ,@RequestBody SearchRequest searchRequest) throws ParseException {
            return ResponseEntity.ok(cityProvinceServiceImp.filter(typeCity
                    ,price
                    ,rate
                    ,rank
                    ,searchRequest));
    }
    @PostMapping("/{name}")
    public ResponseEntity<?> filter(@PathVariable("typecity") String typeCity
            ,@PathVariable("name") String name
            ,@RequestParam(name = "price") String price
            ,@RequestParam(name = "rate") String rate
            ,@RequestParam(name = "rank") String rank
            ,@RequestBody SearchRequest searchRequest) throws ParseException {
        return ResponseEntity.ok(cityProvinceServiceImp.filterName(typeCity
                ,name
                ,price
                ,rate
                ,rank
                ,searchRequest));
    }
    @PostMapping("/{sort}")
    public ResponseEntity<?> sort(@PathVariable("typecity") String typeCity
            ,@RequestParam(name = "price") String price
            ,@RequestParam(name = "rate") String rate
            ,@RequestParam(name = "rank") String rank
            ,@RequestBody SearchRequest searchRequest, @PathVariable("sort") String sort) throws ParseException {
        if (typeCity.equals("city")){
            List<CitySearchDTO> list =(List<CitySearchDTO>) cityProvinceServiceImp.filter(typeCity
                    ,price
                    ,rate
                    ,rank
                    ,searchRequest);
            return ResponseEntity.ok(cityProvinceServiceImp.sortCity(list,sort));
        }
        else if (typeCity.equals("province")) {
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) cityProvinceServiceImp.filter(typeCity
                    ,price
                    ,rate
                    ,rank
                    ,searchRequest);
            return ResponseEntity.ok(cityProvinceServiceImp.sortProvince(list,sort));
        }
        else return ResponseEntity.ok("OK");
    }
    @PostMapping("/{name}/{sort}")
    public ResponseEntity<?> sortName(@PathVariable("typecity") String typeCity
            ,@RequestParam(name = "price") String price
            ,@RequestParam(name = "rate") String rate
            ,@RequestParam(name = "rank") String rank
            ,@RequestBody SearchRequest searchRequest,@PathVariable("name") String name, @PathVariable("sort") String sort) throws ParseException {
        if (typeCity.equals("city")){
            List<CitySearchDTO> list =(List<CitySearchDTO>) cityProvinceServiceImp.filterName(typeCity,name
                    ,price
                    ,rate
                    ,rank
                    ,searchRequest);
            return ResponseEntity.ok(cityProvinceServiceImp.sortCity(list,sort));
        }
        else if (typeCity.equals("province")) {
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) cityProvinceServiceImp.filterName(typeCity
                    ,name
                    ,price
                    ,rate
                    ,rank
                    ,searchRequest);
            return ResponseEntity.ok(cityProvinceServiceImp.sortProvince(list,sort));
        }
        else return ResponseEntity.ok("OK");    }
}