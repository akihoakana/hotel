package com.cybersoft.hotel_booking.DTO;

import com.cybersoft.hotel_booking.entity.BookingEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillDTO {
    private int id;
    private List<BookingEntity> bookingEntities;
    private List<UsersEntity> usersEntities;
}
