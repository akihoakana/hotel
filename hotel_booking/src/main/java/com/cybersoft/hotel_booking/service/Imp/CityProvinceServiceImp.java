package com.cybersoft.hotel_booking.service.Imp;

import com.cybersoft.hotel_booking.DTO.CityProvinceDTO;
import com.cybersoft.hotel_booking.DTO.CitySearchDTO;
import com.cybersoft.hotel_booking.DTO.HotelSearchDTO;
import com.cybersoft.hotel_booking.DTO.ProvinceSearchDTO;
import com.cybersoft.hotel_booking.entity.AttractionEntity;
import com.cybersoft.hotel_booking.entity.CityEntity;
import com.cybersoft.hotel_booking.entity.HotelEntity;
import com.cybersoft.hotel_booking.entity.ProvinceEntity;
import com.cybersoft.hotel_booking.model.AttractionModel;
import com.cybersoft.hotel_booking.model.CityModel;
import com.cybersoft.hotel_booking.model.HotelModel;
import com.cybersoft.hotel_booking.payload.request.SearchRequest;
import com.cybersoft.hotel_booking.repository.BookingRepository;
import com.cybersoft.hotel_booking.repository.BookingRoomRepository;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.CityService;
import com.cybersoft.hotel_booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class CityProvinceServiceImp implements CityService, ProvinceService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private BookingRoomRepository bookingRoomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    private List<?> findAllByList(String typeCity, List<?> provinceEntityList , SearchRequest searchRequest) throws ParseException {
        if (typeCity.equals("city")){
            List<CitySearchDTO> list = new ArrayList<>();
            for (CityEntity cityEntity : (List<CityEntity>) provinceEntityList) {
                CitySearchDTO citySearchDTO = new CitySearchDTO();
                List<AttractionModel> attractionModels = new ArrayList<>();
                List<HotelModel> hotelModels = new ArrayList<>();

                citySearchDTO.setId(cityEntity.getId());
                citySearchDTO.setNameCity(cityEntity.getCity());
                citySearchDTO.setNameProvince(cityEntity.getProvince().getProvince());
                citySearchDTO.setCountAccommodation(cityEntity.getHotelEntitySet().size());

                Period diff = Period.between(searchRequest.getCheckIn(), searchRequest.getCheckOut());
                citySearchDTO.setDateOfStay(diff.getDays());
                citySearchDTO.setAdultNumber(searchRequest.getMaxOccupyAdult());
                citySearchDTO.setChildNumber(searchRequest.getMaxOccupyChild());
                for (HotelEntity hotelEntity : cityEntity.getHotelEntitySet()) {
                    HotelModel hotelModel = new HotelModel();
                    hotelModel.setId(hotelEntity.getId());
                    hotelModel.setHotelName(hotelEntity.getHotelName());
                    hotelModel.setHotelRank(hotelEntity.getHotelRank());
                    hotelModel.setDescriptionHotel(hotelEntity.getDescription());
                    hotelModel.setImageHotel(hotelEntity.getImage());

                    hotelModel.setRateHotel(Math.random()*100);
                    hotelModel.setCountRateHotel(100);
                    hotelModel.setDescriptionRateHotel("setDescriptionRateHotel");

                    if (searchRequest!=null) {
                        hotelModel.setPriceMin(
                                bookingRoomRepository.findBookingRoomByHotelIdAndAndBookingId(
                                        searchRequest.getCheckIn(),searchRequest.getCheckOut()
                                        ,hotelEntity.getId(),searchRequest.getMaxOccupyAdult()
                                        ,searchRequest.getMaxOccupyChild())
                                .get(0).getSub_total_price());
                    } else
                        hotelModel.setPriceMin(0);

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
            return list;

        }
        else {
            List<ProvinceSearchDTO> list = new ArrayList<>();

            for (ProvinceEntity provinceEntity : (List<ProvinceEntity>) provinceEntityList) {
                int countAccommodationHotel = 0;
                ProvinceSearchDTO provinceSearchDTO = new ProvinceSearchDTO();
                List<CityModel> cityModels = new ArrayList<>();
                List<HotelModel> hotelModels = new ArrayList<>();

                provinceSearchDTO.setId(provinceEntity.getId());
                provinceSearchDTO.setNameProvince(provinceEntity.getProvince());

                Period diff = Period.between(searchRequest.getCheckIn(), searchRequest.getCheckOut());
                provinceSearchDTO.setDateOfStay(diff.getDays());
                provinceSearchDTO.setAdultNumber(searchRequest.getMaxOccupyAdult());
                provinceSearchDTO.setChildNumber(searchRequest.getMaxOccupyChild());

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

                    hotelModel.setRateHotel(Math.random()*100);
                    hotelModel.setCountRateHotel(100);
                    hotelModel.setDescriptionRateHotel("setDescriptionRateHotel");

                        if (searchRequest!=null) {
                        hotelModel.setPriceMin(
                                bookingRoomRepository.findBookingRoomByHotelIdAndAndBookingId(
                                        searchRequest.getCheckIn(),searchRequest.getCheckOut()
                                        ,hotelEntity.getId(),searchRequest.getMaxOccupyAdult()
                                        ,searchRequest.getMaxOccupyChild())
                                        .get(0).getSub_total_price());
                    } else
                        hotelModel.setPriceMin(0);
                    hotelModels.add(hotelModel);
                    }
                }
                provinceSearchDTO.setHotelModels(hotelModels);
                provinceSearchDTO.setCountAccommodation(countAccommodationHotel);

                provinceSearchDTO.setCityModels(cityModels);
                list.add(provinceSearchDTO);
            }
            return list;
        }
    }

    public List<?> findAllByType (String typeCity, SearchRequest searchRequest) throws ParseException {
        if (typeCity.equals("city")){
            List<CityEntity> cityEntityList = cityRepository.findAll();
            return findAllByList(typeCity,cityEntityList,searchRequest);
        }
        else {
            List<ProvinceEntity> provinceEntityList = provinceRepository.findAll();
            return findAllByList(typeCity,provinceEntityList,searchRequest);
        }
    }
    public List<?> findAllByTypeAndName (String typeCity,String name, SearchRequest searchRequest) throws ParseException {
        if (typeCity.equals("city")){
            List<CityEntity> cityEntityList = cityRepository.findByCity(name);
            return findAllByList(typeCity,cityEntityList,searchRequest);
        }
        else {
            List<ProvinceEntity> provinceEntityList = provinceRepository.findByProvince(name);
            return findAllByList(typeCity,provinceEntityList,searchRequest);
        }
    }
    public List<?> sortName (String typeCity,String name,String sort, SearchRequest searchRequest) throws ParseException {
        if (typeCity.equals("city")) {
            List<CitySearchDTO> list =(List<CitySearchDTO>) findAllByTypeAndName(typeCity,name,searchRequest);
            return sortCity(list,sort);
        }
        else if (typeCity.equals("province")){
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) findAllByTypeAndName(typeCity,name,searchRequest);
            return sortProvince(list,sort);
        }
        else
            return new ArrayList<>();
    }
    public List<?> sort (String typeCity,String sort, SearchRequest searchRequest) throws ParseException {
        if (typeCity.equals("city")) {
            List<CitySearchDTO> list =(List<CitySearchDTO>) findAllByType(typeCity,searchRequest);
            return sortCity(list,sort);
        }
        else if (typeCity.equals("province")){
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) findAllByType(typeCity,searchRequest);
            return sortProvince(list,sort);
        }
        else
            return new ArrayList<>();
    }

    public List<?> findAllAccommodationByType (String typeCity, SearchRequest searchRequest) throws ParseException {
            List<?> list= findAllByType(typeCity,searchRequest);
            return countAllByList(typeCity,list);
    }

    public List<?> findAllAccommodationByTypeAndName (String typeCity,String name, SearchRequest searchRequest) throws ParseException {
        List<?> list= findAllByTypeAndName(typeCity,name,searchRequest);
            return countAllByList(typeCity,list);
    }

    private List<CityProvinceDTO> countAllByList (String typeCity,List<?> list) {
        List<CityProvinceDTO> cityProvinceDTOS= new ArrayList<>();
        if (typeCity.equals("city")){
            for (CitySearchDTO citySearchDTO : (List<CitySearchDTO>) list) {
                CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
                cityProvinceDTO.setId(citySearchDTO.getId());
                cityProvinceDTO.setName(citySearchDTO.getNameCity());
                cityProvinceDTO.setCountAccommodation(citySearchDTO.getHotelModels().size());
                cityProvinceDTOS.add(cityProvinceDTO);
            }
        }
        else {
            for (ProvinceSearchDTO provinceSearchDTO : (List<ProvinceSearchDTO>) list) {
                CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
                cityProvinceDTO.setId(provinceSearchDTO.getId());
                cityProvinceDTO.setName(provinceSearchDTO.getNameProvince());
                cityProvinceDTO.setCountAccommodation(provinceSearchDTO.getHotelModels().size());
                cityProvinceDTOS.add(cityProvinceDTO);
            }
        }
        return cityProvinceDTOS;
    }

    public List<ProvinceSearchDTO> sortProvince(List<ProvinceSearchDTO>list,String sort){
        if (sort.contains("price")){
            for (ProvinceSearchDTO provinceSearchDTO :  list){
                List<HotelModel> hotelModels =provinceSearchDTO.getHotelModels();
                if (sort.equals("pricemax"))
                    hotelModels.sort(Comparator.comparing(HotelModel::getPriceMin).reversed());
                else
                    hotelModels.sort(Comparator.comparing(HotelModel::getPriceMin));
                provinceSearchDTO.setHotelModels(hotelModels);
            }
        }
        else if (sort.contains("rank")){
            for (ProvinceSearchDTO provinceSearchDTO :  list){
                List<HotelModel> hotelModels =provinceSearchDTO.getHotelModels();
                if (sort.equals("rankmax"))
                    hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank).reversed());
                else
                    hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank));
                provinceSearchDTO.setHotelModels(hotelModels);
            }
        }
        else if (sort.contains("rate")){
            for (ProvinceSearchDTO provinceSearchDTO :  list){
                List<HotelModel> hotelModels =provinceSearchDTO.getHotelModels();
                if (sort.equals("ratemax"))
                    hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel).reversed());
                else
                    hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel));
                provinceSearchDTO.setHotelModels(hotelModels);
            }
        }
        else if (!sort.contains("rate") &&!sort.contains("price")&&!sort.contains("rank")){
            list =new ArrayList<>();
        }
        return list;
    }
    public List<CitySearchDTO> sortCity(List<CitySearchDTO>list,String sort){
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
            for (CitySearchDTO citySearchDTO :  list){
                List<HotelModel> hotelModels =citySearchDTO.getHotelModels();
                if (sort.equals("rankmax"))
                    hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank).reversed());
                else
                    hotelModels.sort(Comparator.comparing(HotelModel::getHotelRank));
                citySearchDTO.setHotelModels(hotelModels);
            }
        }
        else if (sort.contains("rate")){
            for (CitySearchDTO citySearchDTO :  list){
                List<HotelModel> hotelModels =citySearchDTO.getHotelModels();
                if (sort.equals("ratemax"))
                    hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel).reversed());
                else
                    hotelModels.sort(Comparator.comparing(HotelModel::getRateHotel));                citySearchDTO.setHotelModels(hotelModels);
            }
        }
        else if (!sort.contains("rate") &&!sort.contains("price")&&!sort.contains("rank")){
            list =new ArrayList<>();
        }
        return list;
    }
//    private <T extends CitySearchDTO> void abc(T t,List<?> list,List<CityProvinceDTO> cityProvinceDTOS){
//        for (t: (List<T>) list) {
//            CityProvinceDTO cityProvinceDTO = new CityProvinceDTO();
//            cityProvinceDTO.setId(t.getId());
//            cityProvinceDTO.setName(t.getNameCity());
//            cityProvinceDTO.setCountAccommodation(t.getHotelModels().size());
//            cityProvinceDTOS.add(cityProvinceDTO);
//        }
//    }
    public List<?> filter(String typeCity
            , String price
            , String rate
            , String rank
            ,SearchRequest searchRequest) throws ParseException {

        Predicate<HotelModel> hotelModelPredicatePrice = null;
        Predicate<HotelModel> hotelModelPredicateRate = null;
        Predicate<HotelModel> hotelModelPredicateRank ;
        if (StringUtils.hasText(price)&& (price.length()>1)){
            if (price.contains(">")){
                hotelModelPredicatePrice = hotelModel -> hotelModel.getPriceMin() > Integer.valueOf(price.substring(1));
            }
            else if (price.contains("<")){
                hotelModelPredicatePrice = hotelModel -> hotelModel.getPriceMin()<= Integer.valueOf(price.substring(1));
            }
            else hotelModelPredicatePrice = null;
        }
        if (StringUtils.hasText(rate)&& (rate.length()>1)){
            if (price.contains(">")){
                hotelModelPredicateRate = hotelModel -> hotelModel.getRateHotel() > Integer.valueOf(rate.substring(1));
            }
            else if (price.contains("<")){
                hotelModelPredicateRate = hotelModel -> hotelModel.getRateHotel()<= Integer.valueOf(rate.substring(1));
            }
            else hotelModelPredicateRate = null;
        }
        if (StringUtils.hasText(rank)){
            hotelModelPredicateRank = hotelModel -> hotelModel.getHotelRank() == Integer.valueOf(rank);
        }
        else hotelModelPredicateRank = null;

        if (typeCity.equals("city")) {
            List<CitySearchDTO> list = (List<CitySearchDTO>) findAllByType(typeCity, searchRequest);

            for (CitySearchDTO citySearchDTO : list) {
                List<HotelModel> hotelModels;
                if (hotelModelPredicatePrice != null) {
                    hotelModels = citySearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicatePrice)
                            .collect(Collectors.toList());
                    citySearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRate != null) {
                    hotelModels = citySearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRate)
                            .collect(Collectors.toList());
                    citySearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRank != null) {
                    hotelModels = citySearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRank)
                            .collect(Collectors.toList());
                    citySearchDTO.setHotelModels(hotelModels);
                }
            }
            return list;
        }
        else if (typeCity.equals("province")) {
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) findAllByType(typeCity,searchRequest);
            for (ProvinceSearchDTO provinceSearchDTO : list){
                List<HotelModel> hotelModels;
                if (hotelModelPredicatePrice!=null)
                {
                    hotelModels = provinceSearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicatePrice)
                            .collect(Collectors.toList());
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRate!=null){
                    hotelModels = provinceSearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRate)
                            .collect(Collectors.toList());
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRank!=null){
                    hotelModels = provinceSearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRank)
                            .collect(Collectors.toList());
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
            }
            return list;
        }
        return new ArrayList<>();
    }
    public List<?> filterName(String typeCity
            , String name
            , String price
            , String rate
            , String rank
            ,SearchRequest searchRequest) throws ParseException {

        Predicate<HotelModel> hotelModelPredicatePrice = null;
        Predicate<HotelModel> hotelModelPredicateRate = null;
        Predicate<HotelModel> hotelModelPredicateRank ;
        if (StringUtils.hasText(price)&& (price.length()>1)){
            if (price.contains(">")){
                hotelModelPredicatePrice = hotelModel -> hotelModel.getPriceMin() > Integer.valueOf(price.substring(1));
            }
            else if (price.contains("<")){
                hotelModelPredicatePrice = hotelModel -> hotelModel.getPriceMin()<= Integer.valueOf(price.substring(1));
            }
            else hotelModelPredicatePrice = null;
        }
        if (StringUtils.hasText(rate)&& (rate.length()>1)){
            if (price.contains(">")){
                hotelModelPredicateRate = hotelModel -> hotelModel.getRateHotel() > Integer.valueOf(rate.substring(1));
            }
            else if (price.contains("<")){
                hotelModelPredicateRate = hotelModel -> hotelModel.getRateHotel()<= Integer.valueOf(rate.substring(1));
            }
            else hotelModelPredicateRate = null;
        }
        if (StringUtils.hasText(rank)){
            hotelModelPredicateRank = hotelModel -> hotelModel.getHotelRank() == Integer.valueOf(rank);
        }
        else hotelModelPredicateRank = null;

        if (typeCity.equals("city")) {
            List<CitySearchDTO> list = (List<CitySearchDTO>) findAllByTypeAndName(typeCity,name, searchRequest);

            for (CitySearchDTO citySearchDTO : list) {
                List<HotelModel> hotelModels;
                if (hotelModelPredicatePrice != null) {
                    hotelModels = citySearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicatePrice)
                            .collect(Collectors.toList());
                    citySearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRate != null) {
                    hotelModels = citySearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRate)
                            .collect(Collectors.toList());
                    citySearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRank != null) {
                    hotelModels = citySearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRank)
                            .collect(Collectors.toList());
                    citySearchDTO.setHotelModels(hotelModels);
                }
            }
            return list;
        }
        else if (typeCity.equals("province")) {
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) findAllByTypeAndName(typeCity,name, searchRequest);
            for (ProvinceSearchDTO provinceSearchDTO : list){
                List<HotelModel> hotelModels;
                if (hotelModelPredicatePrice!=null)
                {
                    hotelModels = provinceSearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicatePrice)
                            .collect(Collectors.toList());
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRate!=null){
                    hotelModels = provinceSearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRate)
                            .collect(Collectors.toList());
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
                if (hotelModelPredicateRank!=null){
                    hotelModels = provinceSearchDTO.getHotelModels().stream()
                            .filter(hotelModelPredicateRank)
                            .collect(Collectors.toList());
                    provinceSearchDTO.setHotelModels(hotelModels);
                }
            }
            return list;
        }
        return new ArrayList<>();
    }
}
