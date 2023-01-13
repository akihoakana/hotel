package com.cybersoft.hotel_booking.DTO;

import com.cybersoft.hotel_booking.model.AttractionModel;
import com.cybersoft.hotel_booking.model.CityProvinceModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CitySearchDTO extends CityProvinceModel {
    private int id;
    private String nameCity;
    private String nameProvince;

    /**
     *  Attraction
     */
    private List<AttractionModel> attractionModels;
}
