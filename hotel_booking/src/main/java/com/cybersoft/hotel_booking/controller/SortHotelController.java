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
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


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
        if (typeCity.equals("city")) {
            List<CitySearchDTO> list =(List<CitySearchDTO>) cityProvinceServiceImp.findAllByType(typeCity);
            if (sort.contains("price")){
                for (CitySearchDTO citySearchDTO : list){
                    List<HotelModel> hotelModels =citySearchDTO.getHotelModels();
                    if (sort.equals("pricemax"))
                        hotelModels.sort(Comparator.comparing(HotelModel::getPriceMin).reversed());
                    else
                        hotelModels.sort(Comparator.comparing(HotelModel::getPriceMin));
                    citySearchDTO.setHotelModels(hotelModels);
                }
            }
            else if (sort.contains("rank")){
                for (CitySearchDTO citySearchDTO : list){
                    List<HotelModel> hotelModels =citySearchDTO.getHotelModels();
                    if (sort.equals("rankmax"))
                        hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank).reversed());
                    else
                        hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank));
                    citySearchDTO.setHotelModels(hotelModels);
                }
            }
            else if (sort.contains("rate")){
//            list.forEach(
//                    CitySearchDTO::setHotelModels(
//                            (List<HotelModel>) citySearchDTO.getHotelModels()
//                                    .stream()
//                                    .sorted(Comparator.comparing(HotelModel::getRateHotel).reversed())));
                for (CitySearchDTO citySearchDTO : list){
                    List<HotelModel> hotelModels =citySearchDTO.getHotelModels();
                    if (sort.equals("ratemax"))
                        hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel).reversed());
                    else
                        hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel));                citySearchDTO.setHotelModels(hotelModels);
                }
            }
            return ResponseEntity.ok(list);
        }
        else if (typeCity.equals("province")){
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) cityProvinceServiceImp.findAllByType(typeCity);
            if (sort.contains("price")){
                for (ProvinceSearchDTO provinceSearchDTO : list){
                    List<HotelModel> hotelModels =provinceSearchDTO.getHotelModels();
                    if (sort.equals("pricemax"))
                        hotelModels.sort(Comparator.comparing(HotelModel::getPriceMin).reversed());
                    else
                        hotelModels.sort(Comparator.comparing(HotelModel::getPriceMin));
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
            }
            else if (sort.contains("rank")){
                for (ProvinceSearchDTO provinceSearchDTO : list){
                    List<HotelModel> hotelModels =provinceSearchDTO.getHotelModels();
                    if (sort.equals("rankmax"))
                        hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank).reversed());
                    else
                        hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank));
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
            }
            else if (sort.contains("rate")){
                for (ProvinceSearchDTO provinceSearchDTO : list){
                        List<HotelModel> hotelModels =provinceSearchDTO.getHotelModels();
                        if (sort.equals("ratemax"))
                            hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel).reversed());
                        else
                            hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel));
                        provinceSearchDTO.setHotelModels(hotelModels);
                    }
            }
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok("OK");
    }
}