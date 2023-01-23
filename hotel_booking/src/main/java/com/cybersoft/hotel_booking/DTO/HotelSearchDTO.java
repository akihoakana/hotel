package com.cybersoft.hotel_booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelSearchDTO {
    private String hotel_name;
    private int room_id;
    private int max_occupy_adult;
    private int max_occupy_child;
    private String check_in;
    private String check_out;
    private String room_category;
    private String bed_category;
    private double sub_total_price;
}