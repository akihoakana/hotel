package com.cybersoft.hotel_booking.DTO;

import com.cybersoft.hotel_booking.model.CityModel;
import com.cybersoft.hotel_booking.model.CityProvinceModel;
import com.cybersoft.hotel_booking.model.HotelModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProvinceSearchDTO extends CityProvinceModel {
    private int id;
    private String nameProvince;
    protected List<CityModel> cityModels;

}
