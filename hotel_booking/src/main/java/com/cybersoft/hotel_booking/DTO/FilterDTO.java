package com.cybersoft.hotel_booking.DTO;

import com.cybersoft.hotel_booking.entity.AttractionEntity;
import com.cybersoft.hotel_booking.entity.RoomCategoryEntity;
import com.cybersoft.hotel_booking.entity.ServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterDTO {
    private double price;
    private List<ServiceEntity> serviceEntities;
    private List<RoomCategoryEntity> roomCategoryEntities;
    private List<AttractionEntity> attractionEntities;
    private int hotelRank;
}
