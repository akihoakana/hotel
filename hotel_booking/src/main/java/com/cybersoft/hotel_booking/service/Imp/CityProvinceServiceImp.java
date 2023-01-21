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
import com.cybersoft.hotel_booking.repository.BookingRepository;
import com.cybersoft.hotel_booking.repository.BookingRoomRepository;
import com.cybersoft.hotel_booking.repository.CityRepository;
import com.cybersoft.hotel_booking.repository.ProvinceRepository;
import com.cybersoft.hotel_booking.service.CityService;
import com.cybersoft.hotel_booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;


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
    private List<?> findAllByList(String typeCity, List<?> provinceEntityList ,int bookingId) {
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

                citySearchDTO.setDateOfStay(2);
                citySearchDTO.setAdultNumber(2);
                citySearchDTO.setChildNumber(2);

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

                    if (bookingId!=0)
                        hotelModel.setPriceMin(bookingRoomRepository.findBookingRoomByHotelIdAndAndBookingId(hotelEntity.getId(),bookingId).get(0).getPrice());
                    else
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

                provinceSearchDTO.setDateOfStay(2);
                provinceSearchDTO.setAdultNumber(2);
                provinceSearchDTO.setChildNumber(2);

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

                    hotelModel.setPriceMin(bookingRoomRepository.findBookingRoomByHotelIdAndAndBookingId(hotelEntity.getId(),bookingId).get(0).getPrice());
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

    public List<?> findAllByType (String typeCity,int bookingId) {
        if (typeCity.equals("city")){
            List<CityEntity> cityEntityList = cityRepository.findAll();
            return findAllByList(typeCity,cityEntityList,bookingId);
        }
        else {
            List<ProvinceEntity> provinceEntityList = provinceRepository.findAll();
            return findAllByList(typeCity,provinceEntityList,bookingId);
        }
    }
    public List<?> findAllByTypeAndName (String typeCity,String name,int bookingId) {
        if (typeCity.equals("city")){
            List<CityEntity> cityEntityList = cityRepository.findByCity(name);
            return findAllByList(typeCity,cityEntityList,bookingId);
        }
        else {
            List<ProvinceEntity> provinceEntityList = provinceRepository.findByProvince(name);
            return findAllByList(typeCity,provinceEntityList,bookingId);
        }
    }
    public List<?> searchName (String typeCity,String name,String sort,int bookingId) {
        if (typeCity.equals("city")) {
            List<CitySearchDTO> list =(List<CitySearchDTO>) findAllByTypeAndName(typeCity,name,bookingId);
            return sortCity(list,sort);
        }
        else if (typeCity.equals("province")){
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) findAllByTypeAndName(typeCity,name,bookingId);
            return sortProvince(list,sort);
        }
        else
            return new ArrayList<>();
    }
    public List<?> search (String typeCity,String sort,int bookingId) {
        if (typeCity.equals("city")) {
            List<CitySearchDTO> list =(List<CitySearchDTO>) findAllByType(typeCity,bookingId);
            return sortCity(list,sort);
        }
        else if (typeCity.equals("province")){
            List<ProvinceSearchDTO> list =(List<ProvinceSearchDTO>) findAllByType(typeCity,bookingId);
            return sortProvince(list,sort);
        }
        else
            return new ArrayList<>();
    }

    public List<?> findAllAccommodationByType (String typeCity,int bookingId) {
            List<?> list= findAllByType(typeCity,bookingId);
            return countAllByList(typeCity,list);
    }

    public List<?> findAllAccommodationByTypeAndName (String typeCity,String name,int bookingId){
        List<?> list= findAllByTypeAndName(typeCity,name,bookingId);
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

    private List<ProvinceSearchDTO> sortProvince(List<ProvinceSearchDTO>list,String sort){
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
    private List<CitySearchDTO> sortCity(List<CitySearchDTO>list,String sort){
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
}
