package com.cybersoft.hotel_booking.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    private String checkIn;
    private String checkOut;
    private int hotelId;
    private int maxOccupyAdult;
    private int maxOccupyChild;
}
