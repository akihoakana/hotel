package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.DTO.CitySearchDTO;
import com.cybersoft.hotel_booking.DTO.FilterDTO;
import com.cybersoft.hotel_booking.model.HotelModel;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.Imp.CityProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityProvinceServiceImp cityProvinceServiceImp;
//    ,@RequestBody FilterDTO filterDTO
    @PostMapping("")
    public ResponseEntity<?> filter(@PathVariable("typecity") String typeCity) {
        if (typeCity.equals("city")){
            String abc = ">80";
            Integer abc1 = Integer.valueOf(abc.substring(1));
            System.out.println("abc1 = " + abc1);
            Predicate<HotelModel> hotelModelPredicate ;
            if (abc.contains(">")){
                hotelModelPredicate = hotelModel -> hotelModel.getPriceMin() > abc1;
            }
            else {
                hotelModelPredicate = hotelModel -> hotelModel.getPriceMin()<= abc1;
            }
            List<CitySearchDTO> list =(List<CitySearchDTO>) cityProvinceServiceImp.findAllByType(typeCity,3);
            for (CitySearchDTO citySearchDTO : list){
                List<HotelModel> hotelModels =citySearchDTO.getHotelModels().stream()
                        .filter(hotelModelPredicate)
                        .filter(hotelModelPredicate)
                        .collect(Collectors.toList());
                citySearchDTO.setHotelModels(hotelModels);
            }
            return ResponseEntity.ok(list);
        }
        else if (typeCity.equals("province")) {
            return ResponseEntity.ok(provinceRepository.findAll());
        }
        return ResponseEntity.ok(new FilterDTO() );
    }
}