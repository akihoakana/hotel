package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;

import java.util.List;


public interface SigninService {
    List<UsersEntity> newPassord(String email, String oldPassword, String newPassword);

}
