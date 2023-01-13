package com.cybersoft.hotel_booking.model;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CityProvinceModel {
    /**
     *  hotel
     */
    private List<HotelModel> hotelModels;
    /**
     *  Search
     */
    private int dateOfStay;
    private int adultNumber;
    private int childNumber;
    private int countAccommodation;
}
