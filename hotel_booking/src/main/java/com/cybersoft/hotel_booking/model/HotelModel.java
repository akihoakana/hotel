package com.cybersoft.hotel_booking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelModel {
    private int id;
    private String hotelName;
    private int hotelRank;
    private String descriptionHotel;
    private String imageHotel;

    /**
     *  Rating
     */
    private double rateHotel;
    private int countRateHotel;
    private String descriptionRateHotel;

    /**
     *  HotelDetail
     */
    private double priceMin;
}
