package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.entity.UsersEntity;

import java.util.List;


public interface SigninService {
    public List<UsersEntity> newPassword(String email, String password, String passwordConfirm);

}
