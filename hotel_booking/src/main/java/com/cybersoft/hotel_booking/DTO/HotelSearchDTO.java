package com.cybersoft.hotel_booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelSearchDTO {
    private int hotel_id;
    private String hotel_name;
    private String address;
    private String description;
    private String image;
    private int hotel_rank;
    private double avg_rate_score;
    private int rate_count;
    private int booking_id;
    private int room_id;
    private int max_occupy_adult;
    private int max_occupy_child;
    private double price;
    private String room_category;
    private String bed_category;
}