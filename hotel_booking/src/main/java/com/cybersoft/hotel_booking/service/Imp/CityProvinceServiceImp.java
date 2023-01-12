package com.cybersoft.hotel_booking.service.Imp;

import com.cybersoft.hotel_booking.DTO.CityProvinceDTO;
import com.cybersoft.hotel_booking.DTO.CitySearchDTO;
import com.cybersoft.hotel_booking.DTO.ProvinceSearchDTO;
import com.cybersoft.hotel_booking.entity.AttractionEntity;
import com.cybersoft.hotel_booking.entity.CityEntity;
import com.cybersoft.hotel_booking.entity.HotelEntity;
import com.cybersoft.hotel_booking.entity.ProvinceEntity;
import com.cybersoft.hotel_booking.model.AttractionModel;
import com.cybersoft.hotel_booking.model.CityModel;
import com.cybersoft.hotel_booking.model.HotelModel;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.CityService;
import com.cybersoft.hotel_booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CityProvinceServiceImp implements CityService, ProvinceService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;


    private List<Object> findAllByList(String typeCity, List<?> provinceEntityList) {
        List<Object> list = new ArrayList<>();
        if (typeCity.equals("city")){
            for (CityEntity cityEntity : (List<CityEntity>) provinceEntityList) {
                CitySearchDTO citySearchDTO = new CitySearchDTO();
                Set<AttractionModel> attractionModels = new HashSet<>();
                Set<HotelModel> hotelModels = new HashSet<>();

                citySearchDTO.setId(cityEntity.getId());
                citySearchDTO.setNameCity(cityEntity.getCity());
                citySearchDTO.setNameProvince(cityEntity.getProvince().getProvince());
                citySearchDTO.setCountAccommodation(cityEntity.getHotelEntitySet().size());

                citySearchDTO.setDateOfStay(2);
                citySearchDTO.setAdultNumber(2);
                citySearchDTO.setChildNumber(2);
                citySearchDTO.setPrice(Math.random());

                for (HotelEntity hotelEntity : cityEntity.getHotelEntitySet()) {
                    HotelModel hotelModel = new HotelModel();
                    hotelModel.setId(hotelEntity.getId());
                    hotelModel.setHotelName(hotelEntity.getHotelName());
                    hotelModel.setHotelRank(hotelEntity.getHotelRank());
                    hotelModel.setDescriptionHotel(hotelEntity.getDescription());
                    hotelModel.setImageHotel(hotelEntity.getImage());

                    hotelModel.setRateHotel(2);
                    hotelModel.setCountRateHotel(2);
                    hotelModel.setDescriptionRateHotel("setDescriptionRateHotel");

                    hotelModel.setPriceMin(2);
                    hotelModels.add(hotelModel);

                    for (AttractionEntity attractionEntity : hotelEntity.getAttractionEntitySet()) {
                        AttractionModel attractionModel = new AttractionModel();
                        attractionModel.setId(attractionEntity.getId());
                        attractionModel.setName(attractionEntity.getName());
                        attractionModel.setDistance(attractionEntity.getDistance());
                        attractionModels.add(attractionModel);
                    }
                }
                citySearchDTO.setHotelModels(hotelModels);
                citySearchDTO.setAttractionModels(attractionModels);
                list.add(citySearchDTO);
            }
        }
        else {
            for (ProvinceEntity provinceEntity : (List<ProvinceEntity>) provinceEntityList) {
                int countAccommodationHotel = 0;
                ProvinceSearchDTO provinceSearchDTO = new ProvinceSearchDTO();
                Set<CityModel> cityModels = new HashSet<>();
                Set<HotelModel> hotelModels = new HashSet<>();

                provinceSearchDTO.setId(provinceEntity.getId());
                provinceSearchDTO.setNameProvince(provinceEntity.getProvince());

                provinceSearchDTO.setDateOfStay(2);
                provinceSearchDTO.setAdultNumber(2);
                provinceSearchDTO.setChildNumber(2);
                provinceSearchDTO.setPrice(Math.random());

                for (CityEntity cityEntity : provinceEntity.getCityEntitySet()) {
                    CityModel cityModel=new CityModel();
                    cityModel.setId(cityEntity.getId());
                    cityModel.setName(cityEntity.getCity());
                    cityModels.add(cityModel);
                    countAccommodationHotel += cityEntity.getHotelEntitySet().size();

                    for (HotelEntity hotelEntity : cityEntity.getHotelEntitySet()) {
                    HotelModel hotelModel=new HotelModel();
                    hotelModel.setId(hotelEntity.getId());
                    hotelModel.setHotelName(hotelEntity.getHotelName());
                    hotelModel.setHotelRank(hotelEntity.getHotelRank());
                    hotelModel.setDescriptionHotel(hotelEntity.getDescription());
                    hotelModel.setImageHotel(hotelEntity.getImage());

                    hotelModel.setRateHotel(2);
                    hotelModel.setCountRateHotel(2);
                    hotelModel.setDescriptionRateHotel("setDescriptionRateHotel");

                    hotelModel.setPriceMin(2);
                    hotelModels.add(hotelModel);
                    }
                }
                provinceSearchDTO.setHotelModels(hotelModels);
                provinceSearchDTO.setCountAccommodation(countAccommodationHotel);

                provinceSearchDTO.setCityModels(cityModels);
                list.add(provinceSearchDTO);
            }
        }
        return list;
    }

    public List<Object> findAllByType (String typeCity) {
        if (typeCity.equals("city")){
            List<CityEntity> cityEntityList = cityRepository.findAll();
            return findAllByList(typeCity,cityEntityList);
        }
        else {
            List<ProvinceEntity> provinceEntityList = provinceRepository.findAll();
            return findAllByList(typeCity,provinceEntityList);
        }
    }

    public List<?> findAllAccommodationByType (String typeCity) {
        if (typeCity.equals("city")) {
            List<?> list= findAllByList(typeCity,cityRepository.findAll());
            return countAllByList(typeCity,list);
        }
        else {
            List<?> list= findAllByList(typeCity,provinceRepository.findAll());
            return countAllByList(typeCity,list);
        }
    }

    public List<?> findAllAccommodationByTypeAndName (String typeCity,String name){
        if (typeCity.equals("city")) {
            List<?> list= findAllByList(typeCity,cityRepository.findByCity(name));
            return countAllByList(typeCity,list);
        }
        else {
            List<?> list= findAllByList(typeCity,provinceRepository.findByProvince(name));
            return countAllByList(typeCity,list);
        }
    }

    private List<?> countAllByList (String typeCity,List<?> list) {
        List<CityProvinceDTO> cityProvinceDTOS= new ArrayList<>();

        if (typeCity.equals("city")){
            for (CityEntity cityEntity : (List<CityEntity>) list) {
                CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
                cityProvinceDTO.setId(cityEntity.getId());
                cityProvinceDTO.setName(cityEntity.getCity());
                cityProvinceDTO.setCountAccommodation(cityEntity.getHotelEntitySet().size());
                cityProvinceDTOS.add(cityProvinceDTO);
            }
        }
        else {
            for (ProvinceEntity provinceEntity : (List<ProvinceEntity>) list) {
                CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
                cityProvinceDTO.setId(provinceEntity.getId());
                cityProvinceDTO.setName(provinceEntity.getProvince());
                int countAccommodationHotel = 0;

                for (CityEntity cityEntity : provinceEntity.getCityEntitySet()) {
                    countAccommodationHotel += cityEntity.getHotelEntitySet().size();
                }
                cityProvinceDTO.setCountAccommodation(countAccommodationHotel);
                cityProvinceDTOS.add(cityProvinceDTO);
            }
        }
        return cityProvinceDTOS;
    }

//    public List<CityProvinceDTO> findAllAccommodationByType (String typeCity) {
//        if (typeCity.equals("city")){
//            List<CityEntity> cityEntityList = cityRepository.findAll();
//            return countAllByList(typeCity,cityEntityList);
//        }
//        else {
//            List<ProvinceEntity> provinceEntityList = provinceRepository.findAll();
//            return countAllByList(typeCity,provinceEntityList);
//        }
//    }
//    public List<CityProvinceDTO> findAllAccommodationByTypeAndName (String typeCity,String name){
//        if (typeCity.equals("city")){
//            List<CityEntity> cityEntityList = cityRepository.findByCity(name);
//            return countAllByList(typeCity,cityEntityList);
//        }
//        else {
//            List<ProvinceEntity> provinceEntityList = provinceRepository.findByProvince(name);
//            return countAllByList(typeCity,provinceEntityList);
//        }
//    }


//    private List<CityProvinceDTO> countAllByList(String typeCity,List<?> provinceEntityList) {
//        List<CityProvinceDTO> list = new ArrayList<>();
//        if (typeCity.equals("city")){
//            List<CityEntity> cityEntityList = (List<CityEntity>) provinceEntityList;
//            for (CityEntity cityEntity : cityEntityList) {
//                CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
//                cityProvinceDTO.setId(cityEntity.getId());
//                cityProvinceDTO.setName(cityEntity.getCity());
//                cityProvinceDTO.setCountAccommodation(cityEntity.getHotelEntitySet().size());
//                list.add(cityProvinceDTO);
//            }
//        }
//        else {
//            for (ProvinceEntity provinceEntity : (List<ProvinceEntity>) provinceEntityList) {
//                CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
//                cityProvinceDTO.setId(provinceEntity.getId());
//                cityProvinceDTO.setName(provinceEntity.getProvince());
//                int countAccommodationHotel = 0;
//
//                for (CityEntity cityEntity : provinceEntity.getCityEntitySet()) {
//                    countAccommodationHotel += cityEntity.getHotelEntitySet().size();
//                }
//                cityProvinceDTO.setCountAccommodation(countAccommodationHotel);
//                list.add(cityProvinceDTO);
//            }
//        }
//        return list;
//    }
}
