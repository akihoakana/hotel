package com.cybersoft.hotel_booking.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class SearchRequest {
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int maxOccupyAdult;
    private int maxOccupyChild;
}
