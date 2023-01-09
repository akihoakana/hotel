package com.cybersoft.hotel_booking.model;

import com.cybersoft.hotel_booking.entity.UsersEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseModel {
    private UsersEntity usersEntity;
    private String linkActive;
}
